package com.rcarmona.censo.domain.municipio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase MunicipioModel, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioModelTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class MunicipioModelTest {

    @Test
    void shouldCreateNewMunicipioSuccessfully() {
        MunicipioModel municipio = MunicipioModel.createNew("Cartagena", 1);

        assertNull(municipio.getId());
        assertEquals("Cartagena", municipio.getNombre());
        assertEquals(1, municipio.getProvinciaId());
    }

    @Test
    void shouldThrowExceptionWhenNombreIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MunicipioModel.createNew(null, 1);
        });
        assertEquals("El nombre del municipio es obligatorio", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNombreIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MunicipioModel.createNew("   ", 1);
        });
        assertEquals("El nombre del municipio es obligatorio", exception.getMessage());
    }

    @Test
    void shouldTrimNombre() {
        MunicipioModel municipio = MunicipioModel.createNew("  Barranquilla  ", 2);
        assertEquals("Barranquilla", municipio.getNombre());
    }
}
