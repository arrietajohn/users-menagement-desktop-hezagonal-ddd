package com.rcarmona.censo.infrastructure.adapter.persistence.exception;

/**
 * ExcepciÃ³n base para la capa de Persistencia.
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Excepción personalizada del sistema. Maneja flujos de error controlados para situaciones específicas de Persistence, devolviendo mensajes de error claros y semánticos al usuario final en lugar de fallos genéricos.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: PersistenceException
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}


