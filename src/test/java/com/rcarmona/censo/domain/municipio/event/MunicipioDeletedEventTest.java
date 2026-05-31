package com.rcarmona.censo.domain.municipio.event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase MunicipioDeletedEvent, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioDeletedEventTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class MunicipioDeletedEventTest {
    @Test
    void deleteEvent_ShouldInitializeCorrectly() {
        MunicipioDeletedEvent event = new MunicipioDeletedEvent("123");
        assertEquals("123", event.getMunicipioId());
        assertNotNull(event.occurredOn());
    }
}



