package com.rcarmona.censo.domain.municipio.exception;

/**
 * Clase base para todas las excepciones del Dominio.
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
 * - Excepción personalizada del sistema. Maneja flujos de error controlados para situaciones específicas de Domain, devolviendo mensajes de error claros y semánticos al usuario final en lugar de fallos genéricos.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: DomainException
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}


