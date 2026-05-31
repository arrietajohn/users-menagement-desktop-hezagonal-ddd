package com.rcarmona.censo.domain.municipio.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase CategoriaMunicipio, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CategoriaMunicipioTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class CategoriaMunicipioTest {
    @Test
    void values_ShouldContainAllCategories() {
        CategoriaMunicipio[] values = CategoriaMunicipio.values();
        assertEquals(3, values.length);
        assertNotNull(CategoriaMunicipio.valueOf("CAPITAL"));
    }
}



