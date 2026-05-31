package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveSucursalPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSucursalCommand;
import com.jcaa.usersmanagement.domain.exception.SucursalAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.BancoId;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import com.jcaa.usersmanagement.domain.valueobject.SucursalCityOperation;
import com.jcaa.usersmanagement.domain.valueobject.SucursalDirection;
import com.jcaa.usersmanagement.domain.valueobject.SucursalNumber;
import com.jcaa.usersmanagement.domain.valueobject.SucursalPostalCode;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para CreateSucursalService.
 *
 * <p>Cubre: validación del command, ID duplicado y flujo feliz (save exitoso).
 */
@DisplayName("CreateSucursalService")
@ExtendWith(MockitoExtension.class)
class CreateSucursalServiceTest {

    @Mock private SaveSucursalPort saveSucursalPort;
    @Mock private GetSucursalByIdPort getSucursalByIdPort;

    private CreateSucursalService service;

    private static final SucursalModel SUCURSAL_FIXTURE = new SucursalModel(
            new SucursaId("S-001"),
            new SucursalNumber("1"),
            new SucursalDirection("Calle Principal 123"),
            new SucursalPostalCode("080001"),
            new SucursalCityOperation("Barranquilla"),
            new BancoId("1")
    );

    @BeforeEach
    void setUp() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            service = new CreateSucursalService(saveSucursalPort, getSucursalByIdPort, factory.getValidator());
        }
    }

    // ── flujo feliz

    @Test
    @DisplayName("execute() guarda la sucursal cuando el ID no existe previamente")
    void shouldSaveSucursalWhenIdIsNew() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "Calle Principal 123", "080001", "Barranquilla", "1");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.empty());
        when(saveSucursalPort.save(any())).thenReturn(SUCURSAL_FIXTURE);

        // Act
        final SucursalModel result = service.execute(command);

        // Assert
        assertAll(
                "flujo feliz de CreateSucursalService",
                () -> assertNotNull(result, "el resultado no debe ser null"),
                () -> assertEquals("S-001", result.getId().value(), "el ID debe coincidir"),
                () -> assertEquals("1", result.getNumero().value(), "el número debe coincidir"),
                () -> assertEquals("Barranquilla", result.getCiudad().value(), "la ciudad debe coincidir")
        );

        verify(saveSucursalPort).save(any(SucursalModel.class));
    }

    // ── ID duplicado

    @Test
    @DisplayName("execute() lanza SucursalAlreadyExistsException cuando el ID ya existe")
    void shouldThrowWhenSucursalIdAlreadyExists() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "Calle Principal 123", "080001", "Barranquilla", "1");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.of(SUCURSAL_FIXTURE));

        // Act & Assert
        assertThrows(SucursalAlreadyExistsException.class, () -> service.execute(command));
        verify(saveSucursalPort, never()).save(any());
    }

    // ── validación del command

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el ID está en blanco")
    void shouldThrowWhenIdIsBlank() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("", "1", "Calle Principal 123", "080001", "Barranquilla", "1");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el número está en blanco")
    void shouldThrowWhenNumeroIsBlank() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "", "Calle Principal 123", "080001", "Barranquilla", "1");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando la dirección está en blanco")
    void shouldThrowWhenDireccionIsBlank() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "", "080001", "Barranquilla", "1");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando la ciudad está en blanco")
    void shouldThrowWhenCiudadIsBlank() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "Calle Principal 123", "080001", "", "1");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el código postal está en blanco")
    void shouldThrowWhenCodigoPostalIsBlank() {
        // Arrange
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "Calle Principal 123", "", "Barranquilla", "1");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el banco ID es nulo")
    void shouldThrowWhenBancoIdIsNull() {
        // Arrange — bancoId tiene @NotNull (no @NotBlank), así que null dispara la validación
        final CreateSucursalCommand command =
                new CreateSucursalCommand("S-001", "1", "Calle Principal 123", "080001", "Barranquilla", null);

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(saveSucursalPort, getSucursalByIdPort);
    }
}
