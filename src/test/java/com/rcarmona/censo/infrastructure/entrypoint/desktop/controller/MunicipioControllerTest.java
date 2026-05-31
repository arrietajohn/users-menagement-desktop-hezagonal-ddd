package com.rcarmona.censo.infrastructure.entrypoint.desktop.controller;

import static org.mockito.Mockito.*;

import com.rcarmona.censo.application.municipio.port.in.*;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;

/**
 * Pruebas unitarias para MunicipioController.
 * @author Rosary Carmona
 */
@ExtendWith(MockitoExtension.class)
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase MunicipioController, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioControllerTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class MunicipioControllerTest {

    @Mock private CreateMunicipioUseCase createUseCase;
    @Mock private UpdateMunicipioUseCase updateUseCase;
    @Mock private DeleteMunicipioUseCase deleteUseCase;
    @Mock private GetMunicipioByIdUseCase getByIdUseCase;
    @Mock private GetAllMunicipiosUseCase getAllUseCase;
    @Mock private CountMunicipiosUseCase countUseCase;
    @Mock private FindMunicipiosByProvinciaUseCase findByProvinciaUseCase;
    @Mock private SearchMunicipiosByNameUseCase searchByNameUseCase;
    @Mock private CensoConsoleIO console;

    private MunicipioController controller;

    @BeforeEach
    void setUp() {
        controller = new MunicipioController(
            createUseCase, updateUseCase, deleteUseCase, getByIdUseCase, 
            getAllUseCase, countUseCase, findByProvinciaUseCase, searchByNameUseCase, console
        );
    }

    @Test
    void shouldExitMenuWhenOption9IsSelected() {
        when(console.readInt(anyString())).thenReturn(9);
        
        controller.displayMenu();
        
        verify(console, times(1)).readInt(anyString());
    }

    @Test
    void shouldCallGetAllUseCaseWhenOption5IsSelected() {
        when(console.readInt(anyString())).thenReturn(5).thenReturn(9);
        when(getAllUseCase.execute()).thenReturn(Collections.emptyList());
        
        controller.displayMenu();
        
        verify(getAllUseCase, times(1)).execute();
    }
}



