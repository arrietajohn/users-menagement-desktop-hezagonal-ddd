package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.DeleteSucursalPort;
import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteSucursalCommand;
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
 * Tests para DeleteSucursalService.
 *
 * <p>Cubre: flujo feliz (delete invocado), sucursal no encontrada y validación del command.
 */
@DisplayName("DeleteSucursalService")
@ExtendWith(MockitoExtension.class)
class DeleteSucursalServiceTest {

    @Mock private DeleteSucursalPort deleteSucursalPort;
    @Mock private GetSucursalByIdPort getSucursalByIdPort;

    private DeleteSucursalService service;

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
            service = new DeleteSucursalService(deleteSucursalPort, getSucursalByIdPort, factory.getValidator());
        }
    }

    // ── flujo feliz

    @Test
    @DisplayName("execute() invoca deleteSucursalPort cuando la sucursal existe")
    void shouldDeleteWhenSucursalExists() {
        // Arrange
        final DeleteSucursalCommand command = new DeleteSucursalCommand("S-001");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.of(SUCURSAL_FIXTURE));

        // Act
        service.execute(command);

        // Assert
        verify(deleteSucursalPort).delete(new SucursaId("S-001"));
    }

    @Test
    @DisplayName("execute() no invoca getSucursalByIdPort más de una vez por ejecución")
    void shouldCheckExistenceExactlyOnce() {
        // Arrange
        final DeleteSucursalCommand command = new DeleteSucursalCommand("S-001");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.of(SUCURSAL_FIXTURE));

        // Act
        service.execute(command);

        // Assert
        verify(getSucursalByIdPort, times(1)).getById(any());
    }

    // ── sucursal no encontrada

    @Test
    @DisplayName("execute() lanza SucursalNotFoundException cuando el ID no existe")
    void shouldThrowWhenSucursalNotFound() {
        // Arrange
        final DeleteSucursalCommand command = new DeleteSucursalCommand("NO-EXISTE");

        when(getSucursalByIdPort.getById(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SucursalNotFoundException.class, () -> service.execute(command));
        verify(deleteSucursalPort, never()).delete(any());
    }

    // ── validación del command

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el ID está en blanco")
    void shouldThrowWhenIdIsBlank() {
        // Arrange
        final DeleteSucursalCommand command = new DeleteSucursalCommand("  ");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(deleteSucursalPort, getSucursalByIdPort);
    }

    @Test
    @DisplayName("execute() lanza ConstraintViolationException cuando el ID es cadena vacía")
    void shouldThrowWhenIdIsEmpty() {
        // Arrange
        final DeleteSucursalCommand command = new DeleteSucursalCommand("");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> service.execute(command));
        verifyNoInteractions(deleteSucursalPort, getSucursalByIdPort);
    }
}
