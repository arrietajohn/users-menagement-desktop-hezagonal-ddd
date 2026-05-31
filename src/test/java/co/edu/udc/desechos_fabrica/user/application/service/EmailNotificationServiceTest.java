package co.edu.udc.desechos_fabrica.user.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.user.application.port.out.EmailSenderPort;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.EmailSenderException;
import co.edu.udc.desechos_fabrica.user.domain.model.EmailDestinationModel;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para EmailNotificationService.
 *
 * <p>Cubre: flujos felices de notificación, re-lanzamiento de EmailSenderException, template no
 * encontrado (is == null) e IOException al leer el template.
 */
@DisplayName("EmailNotificationService")
@ExtendWith(MockitoExtension.class)
class EmailNotificationServiceTest {

  @Mock private EmailSenderPort emailSenderPort;

  private EmailNotificationService service;

  private static final String EMAIL = "john@example.com";
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Arrieta";
  private static final String PASSWORD = "SecurePass1";
  private static final String TEMPLATE_CONTENT =
      "<html>{{firstName}} {{lastName}} {{email}} {{password}} {{role}} {{status}}</html>";

  private UserModel user;

  @BeforeEach
  void setUp() {
    // Usamos un spy para poder mockear solo el método openResourceStream
    service = spy(new EmailNotificationService(emailSenderPort));

    user =
        new UserModel(
            new UserFirstName(FIRST_NAME),
            new UserLastName(LAST_NAME),
            new UserEmail(EMAIL),
            UserPassword.fromPlainText(PASSWORD),
            UserRole.REVIEWER,
            UserStatus.ACTIVE);
  }

  // ── notifyUserCreated() — flujo feliz

  @Test
  @DisplayName("notifyUserCreated() invoca el puerto con el email y asunto correctos")
  void shouldSendCreatedNotificationToCorrectEmail() {
    // Arrange
    doReturn(new ByteArrayInputStream(TEMPLATE_CONTENT.getBytes(StandardCharsets.UTF_8)))
        .when(service)
        .openResourceStream(any());
    // Act
    service.notifyUserCreated(user, PASSWORD);

    // Assert
    verify(emailSenderPort)
        .send(
            argThat(
                dest ->
                    EMAIL.equals(dest.getDestinationEmail())
                        && dest.getSubject().contains("creada")));
  }

  // ── notifyUserUpdated() — flujo feliz

  @Test
  @DisplayName("notifyUserUpdated() invoca el puerto con el email y asunto correctos")
  void shouldSendUpdatedNotificationToCorrectEmail() {
    // Arrange
    doReturn(new ByteArrayInputStream(TEMPLATE_CONTENT.getBytes(StandardCharsets.UTF_8)))
        .when(service)
        .openResourceStream(any());
    // Act
    service.notifyUserUpdated(user);

    // Assert
    verify(emailSenderPort)
        .send(
            argThat(
                dest ->
                    EMAIL.equals(dest.getDestinationEmail())
                        && dest.getSubject().contains("actualizada")));
  }

  // ── re-lanzar EmailSenderException en notifyUserCreated

  @Test
  @DisplayName("notifyUserCreated() re-lanza EmailSenderException cuando el puerto falla")
  void shouldRethrowEmailSenderExceptionOnCreate() {
    // Arrange
    doReturn(new ByteArrayInputStream(TEMPLATE_CONTENT.getBytes(StandardCharsets.UTF_8)))
        .when(service)
        .openResourceStream(any());
    final EmailSenderException cause =
        EmailSenderException.becauseSmtpFailed(EMAIL, "Connection refused");
    doThrow(cause).when(emailSenderPort).send(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> service.notifyUserCreated(user, PASSWORD));
  }

  // ── re-lanzar EmailSenderException en notifyUserUpdated

  @Test
  @DisplayName("notifyUserUpdated() re-lanza EmailSenderException cuando el puerto falla")
  void shouldRethrowEmailSenderExceptionOnUpdate() {
    // Arrange
    doReturn(new ByteArrayInputStream(TEMPLATE_CONTENT.getBytes(StandardCharsets.UTF_8)))
        .when(service)
        .openResourceStream(any());
    final EmailSenderException cause =
        EmailSenderException.becauseSmtpFailed(EMAIL, "Connection refused");
    doThrow(cause).when(emailSenderPort).send(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> service.notifyUserUpdated(user));
  }

  // ── loadTemplate() — rama: template no encontrado (is == null)

  @Test
  @DisplayName(
      "loadTemplate() lanza EmailSenderException cuando el template no existe en classpath")
  void shouldThrowWhenTemplateNotFound() {
    // Arrange — openResourceStream retorna null simulando template ausente en classpath
    doReturn(null).when(service).openResourceStream(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> service.notifyUserCreated(user, PASSWORD));
  }

  // ── loadTemplate() — rama: IOException al leer el stream

  @Test
  @DisplayName(
      "loadTemplate() lanza EmailSenderException cuando ocurre IOException al leer el stream")
  void shouldThrowWhenTemplateThrowsIOException() throws IOException {
    // Arrange — stream que lanza IOException al invocar readAllBytes()
    final InputStream brokenStream = mock(InputStream.class);
    doThrow(new IOException("Disk error")).when(brokenStream).readAllBytes();
    doReturn(brokenStream).when(service).openResourceStream(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> service.notifyUserCreated(user, PASSWORD));
  }

  // ── renderTemplate() — todos los tokens se sustituyen

  @Test
  @DisplayName("renderTemplate() sustituye todos los tokens del template correctamente")
  void shouldRenderAllTokensInTemplate() {
    // Arrange
    final String templateWithAllTokens = "<html>{{firstName}} {{lastName}} {{email}} {{password}} {{role}} {{status}}</html>";
    doReturn(new ByteArrayInputStream(templateWithAllTokens.getBytes(StandardCharsets.UTF_8)))
        .when(service)
        .openResourceStream(any());
    ArgumentCaptor<EmailDestinationModel> captor =
        ArgumentCaptor.forClass(EmailDestinationModel.class);

    // Act
    service.notifyUserCreated(user, PASSWORD);

    // Assert
    verify(emailSenderPort).send(captor.capture());
    String renderedBody = captor.getValue().getBody();
    assertAll(
        "El cuerpo del email debe contener todos los valores renderizados",
        () -> assertTrue(renderedBody.contains(FIRST_NAME)),
        () -> assertTrue(renderedBody.contains(LAST_NAME)),
        () -> assertTrue(renderedBody.contains(EMAIL)),
        () -> assertTrue(renderedBody.contains(PASSWORD)),
        () -> assertTrue(renderedBody.contains(UserRole.REVIEWER.name())),
        () -> assertTrue(renderedBody.contains(UserStatus.ACTIVE.name())),
        () -> assertFalse(renderedBody.contains("{{")),
        () -> assertFalse(renderedBody.contains("}}")));
  }
}
