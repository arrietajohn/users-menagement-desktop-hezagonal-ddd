package com.rcarmona.censo.application.municipio.port.out;

import com.rcarmona.censo.domain.municipio.model.MunicipioModel;

/**
 * Puerto de Salida para actualizar un Municipio.
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
 * - Interfaz de salida (Out Port) que define el contrato de persistencia o comunicación externa para UpdateMunicipio. Garantiza el Principio de Inversión de Dependencias aislando la base de datos de la lógica central.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: UpdateMunicipioPort
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public interface UpdateMunicipioPort {
    MunicipioModel update(MunicipioModel municipio);
}


