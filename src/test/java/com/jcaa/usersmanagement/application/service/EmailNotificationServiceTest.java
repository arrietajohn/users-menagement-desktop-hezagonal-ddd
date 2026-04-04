package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.EmailSenderPort;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.exception.EmailSenderException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
  @Mock private EmailSenderPort spyEmailSenderPort;

  private EmailNotificationService service;
  private EmailNotificationService serviceSpy;

  private static final String EMAIL = "john@example.com";
  private static final String NAME = "John Arrieta";
  private static final String PASSWORD = "SecurePass1";
  private static final String TEMPLATE_CONTENT =
      "<html>{{name}} {{email}} {{password}} {{role}} {{status}}</html>";

  private UserModel user;

  @BeforeEach
  void setUp() {
    service = new EmailNotificationService(emailSenderPort);
    serviceSpy = spy(new EmailNotificationService(spyEmailSenderPort));

    user =
        new UserModel(
            new UserId("u-001"),
            new UserName(NAME),
            new UserEmail(EMAIL),
            UserPassword.fromPlainText(PASSWORD),
            UserRole.ADMIN,
            UserStatus.ACTIVE);
  }

  // ── notifyUserCreated() — flujo feliz

  @Test
  @DisplayName("notifyUserCreated() invoca el puerto con el email y asunto correctos")
  void shouldSendCreatedNotificationToCorrectEmail() {
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
    doReturn(null).when(serviceSpy).openResourceStream(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> serviceSpy.notifyUserCreated(user, PASSWORD));
  }

  // ── loadTemplate() — rama: IOException al leer el stream

  @Test
  @DisplayName(
      "loadTemplate() lanza EmailSenderException cuando ocurre IOException al leer el stream")
  void shouldThrowWhenTemplateThrowsIOException() throws IOException {
    // Arrange — stream que lanza IOException al invocar readAllBytes()
    final InputStream brokenStream = mock(InputStream.class);
    doThrow(new IOException("Disk error")).when(brokenStream).readAllBytes();
    doReturn(brokenStream).when(serviceSpy).openResourceStream(any());

    // Act & Assert
    assertThrows(EmailSenderException.class, () -> serviceSpy.notifyUserCreated(user, PASSWORD));
  }

  // ── renderTemplate() — todos los tokens se sustituyen

  @Test
  @DisplayName("renderTemplate() sustituye todos los tokens del template correctamente")
  void shouldRenderAllTokensInTemplate() {
    // Arrange — template propio con todos los tokens del método notifyUserCreated
    final InputStream templateStream =
        new ByteArrayInputStream(TEMPLATE_CONTENT.getBytes(StandardCharsets.UTF_8));
    doReturn(templateStream).when(serviceSpy).openResourceStream(any());

    // Act
    serviceSpy.notifyUserCreated(user, PASSWORD);

    // Assert — el body enviado contiene los valores interpolados
    verify(spyEmailSenderPort)
        .send(argThat(dest -> dest.getBody().contains(NAME) && dest.getBody().contains(EMAIL)));
  }
}
