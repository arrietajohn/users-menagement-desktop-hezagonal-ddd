package com.rcarmona.censo.application.municipio.service.dto.command;

/**
 * Comando para actualizar un Municipio.
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
 * - Objeto de Transferencia de Datos (DTO) inmutable. Encapsula y transporta de forma segura los parámetros de entrada desde el exterior hacia los servicios de la aplicación para el proceso de UpdateMunicipio.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: UpdateMunicipioCommand
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public record UpdateMunicipioCommand(
    Integer id,
    String nombre,
    Integer provinciaId
) {}


