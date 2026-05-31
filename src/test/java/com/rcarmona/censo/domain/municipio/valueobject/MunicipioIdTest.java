package com.rcarmona.censo.domain.municipio.valueobject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el Value Object MunicipioId.
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase MunicipioId, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioIdTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class MunicipioIdTest {

    @Test
    void shouldCreateMunicipioIdWhenValid() {
        MunicipioId id = new MunicipioId(1);
        assertNotNull(id);
        assertEquals(1, id.value());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MunicipioId(null));
        assertEquals("El ID del municipio no puede ser nulo", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MunicipioId(-5));
        assertEquals("El ID del municipio no puede ser negativo", exception.getMessage());
    }
}



