package com.rcarmona.censo.application.municipio.port.out;

/**
 * Puerto de Salida para contar los Municipios.
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
 * - Interfaz de salida (Out Port) que define el contrato de persistencia o comunicación externa para CountMunicipios. Garantiza el Principio de Inversión de Dependencias aislando la base de datos de la lógica central.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CountMunicipiosPort
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public interface CountMunicipiosPort {
    long count();
}


