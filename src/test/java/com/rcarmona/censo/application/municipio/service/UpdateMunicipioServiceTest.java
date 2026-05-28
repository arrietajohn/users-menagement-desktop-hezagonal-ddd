package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.out.UpdateMunicipioPort;
import com.rcarmona.censo.application.municipio.port.out.GetMunicipioByIdPort;
import com.rcarmona.censo.application.municipio.service.dto.command.UpdateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.exception.MunicipioNotFoundException;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase UpdateMunicipioService, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: UpdateMunicipioServiceTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class UpdateMunicipioServiceTest {

    @Mock
    private UpdateMunicipioPort updateMunicipioPort;

    @Mock
    private GetMunicipioByIdPort getMunicipioByIdPort;

    @InjectMocks
    private UpdateMunicipioService service;

    @Test
    void execute_WhenMunicipioExists_UpdatesAndReturns() {
        UpdateMunicipioCommand command = new UpdateMunicipioCommand(1, "Turbaco", 13);
        MunicipioModel existing = new MunicipioModel(new MunicipioId(1), "Viejo", 13);
        MunicipioModel updated = new MunicipioModel(new MunicipioId(1), "Turbaco", 13);

        when(getMunicipioByIdPort.findById(any(MunicipioId.class))).thenReturn(Optional.of(existing));
        when(updateMunicipioPort.update(any(MunicipioModel.class))).thenReturn(updated);

        MunicipioModel result = service.execute(command);

        assertEquals("Turbaco", result.getNombre());
        verify(updateMunicipioPort).update(any(MunicipioModel.class));
    }

    @Test
    void execute_WhenNotExists_ThrowsException() {
        UpdateMunicipioCommand command = new UpdateMunicipioCommand(99, "Turbaco", 13);
        when(getMunicipioByIdPort.findById(any(MunicipioId.class))).thenReturn(Optional.empty());

        assertThrows(MunicipioNotFoundException.class, () -> service.execute(command));
        verify(updateMunicipioPort, never()).update(any());
    }
}



