package com.rcarmona.censo.infrastructure.entrypoint.desktop.dto;

/**
 * DTO de respuesta para mostrar los datos de un Municipio en la UI.
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
 * - Objeto de Transferencia de Datos (DTO) de salida. Transporta los resultados de la aplicación de vuelta al usuario, asegurando que el modelo interno (Entidades de Dominio) nunca se exponga directamente hacia afuera.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioResponse
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public record MunicipioResponse(
    Integer id,
    String nombre,
    Integer provinciaId
) {}


