package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateSucursalPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateSucursalCommand;
import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
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
 * Tests para UpdateSucursalService.
 *
 * <p>Cubre: flujo feliz, sucursal no encontrada y validación del command.
 */
@DisplayName("UpdateSucursalService")
@ExtendWith(MockitoExtension.class)
class UpdateSucursalServiceTest {

    @Mock private UpdateSucursalPort updateSucursalPort;
    @Mock private GetSucursalByIdPort getSucursalByIdPort;

    private UpdateSucursalService service;

    private static final String ID = "S-001";

    private SucursalModel existingSucursal;

    @BeforeEach
    void setUp() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            service = new UpdateSucursalService(updateSucursalPort, getSucursalByIdPort, factory.getValidator());
        }

        existingSucursal = new SucursalModel(
                new SucursaId(ID),
                new SucursalNumber("1"),
                new SucursalDirection("Calle Principal 123"),
                new SucursalPostalCode("080001"),
                new SucursalCityOperation("Barranquilla"),
                new BancoId("1")
        );
    }

    // ── flujo feliz

    @Test
    @DisplayName("execute() actualiza la sucursal cuando existe y los datos son válidos")
    void shouldUpdateSucursalWhenItExists() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "2", "Calle Nueva 456", "080002", "Medellin", "2");

        final SucursalModel updatedSucursal = new SucursalModel(
                new SucursaId(ID),
                new SucursalNumber("2"),
                new SucursalDirection("Calle Nueva 456"),
                new SucursalPostalCode("080002"),
                new SucursalCityOperation("Medellin"),
                new BancoId("2")
        );

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.of(existingSucursal));
        when(updateSucursalPort.update(any())).thenReturn(updatedSucursal);

        // Act
        final SucursalModel result = service.execute(command);

        // Assert
        assertAll(
                "flujo feliz de UpdateSucursalService",
                () -> assertNotNull(result, "el resultado no debe ser null"),
                () -> assertEquals("2", result.getNumero().value(), "el número debe actualizarse"),
                () -> assertEquals("Calle Nueva 456", result.getDireccion().value(), "la dirección debe actualizarse"),
                () -> assertEquals("Medellin", result.getCiudad().value(), "la ciudad debe actualizarse")
        );

        verify(updateSucursalPort).update(any(SucursalModel.class));
    }

    // ── sucursal no encontrada

    @Test
    @DisplayName("execute() lanza SucursalNotFoundException cuando el ID no existe")
    void shouldThrowWhenSucursalNotFound() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand("NO-EXISTE", "2", "Calle Nueva 456", "080002", "Medellin", "2");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SucursalNotFoundException.class, () -> service.execute(command));
        verify(updateSucursalPort, never()).update(any());
    }

    // ── validación del command

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el ID está en blanco")
    void shouldThrowWhenIdIsBlank() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand("", "2", "Calle Nueva 456", "080002", "Medellin", "2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el número está en blanco")
    void shouldThrowWhenNumeroIsBlank() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "", "Calle Nueva 456", "080002", "Medellin", "2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando la ciudad está en blanco")
    void shouldThrowWhenCiudadIsBlank() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "2", "Calle Nueva 456", "080002", "", "2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando la dirección está en blanco")
    void shouldThrowWhenDireccionIsBlank() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "2", "", "080002", "Medellin", "2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el código postal está en blanco")
    void shouldThrowWhenCodigoPostalIsBlank() {
        // Arrange
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "2", "Calle Nueva 456", "", "Medellin", "2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el banco ID es nulo")
    void shouldThrowWhenBancoIdIsNull() {
        // Arrange — bancoId tiene @NotNull (no @NotBlank), así que null dispara la validación
        final UpdateSucursalCommand command =
                new UpdateSucursalCommand(ID, "2", "Calle Nueva 456", "080002", "Medellin", null);

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(updateSucursalPort, getSucursalByIdPort);
    }
}
