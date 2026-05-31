package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.out.SaveMunicipioPort;
import com.rcarmona.censo.application.municipio.service.dto.command.CreateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase CreateMunicipioService, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CreateMunicipioServiceTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class CreateMunicipioServiceTest {

    @Mock
    private SaveMunicipioPort saveMunicipioPort;

    @InjectMocks
    private CreateMunicipioService service;

    @Test
    void execute_WhenCalled_SavesMunicipio() {
        // Arrange
        CreateMunicipioCommand command = new CreateMunicipioCommand("Cartagena", 13);
        MunicipioModel savedModel = new MunicipioModel(new MunicipioId(1), "Cartagena", 13);
        
        when(saveMunicipioPort.save(any(MunicipioModel.class))).thenReturn(savedModel);

        // Act
        MunicipioModel result = service.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId().value());
        assertEquals("Cartagena", result.getNombre());
        verify(saveMunicipioPort, times(1)).save(any(MunicipioModel.class));
    }
}



