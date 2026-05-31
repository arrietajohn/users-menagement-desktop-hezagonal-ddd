package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.out.SearchMunicipiosByNamePort;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase SearchMunicipiosByNameService, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: SearchMunicipiosByNameServiceTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class SearchMunicipiosByNameServiceTest {

    @Mock
    private SearchMunicipiosByNamePort port;

    @InjectMocks
    private SearchMunicipiosByNameService service;

    @Test
    void execute_ReturnsList() {
        MunicipioModel model = new MunicipioModel(new MunicipioId(1), "Cartagena", 13);
        when(port.searchByName("Carta")).thenReturn(List.of(model));

        List<MunicipioModel> result = service.execute("Carta");

        assertFalse(result.isEmpty());
        assertTrue(result.get(0).getNombre().contains("Carta"));
    }
}



