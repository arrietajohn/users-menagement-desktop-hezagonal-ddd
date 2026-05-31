package com.rcarmona.censo.application.municipio.port.in;

import com.rcarmona.censo.application.municipio.service.dto.command.CreateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;

/**
 * Caso de Uso: Crear Municipio.
 * 
 * Interfaz segregada para crear un nuevo municipio, aislando esta operaciÃ³n del resto.
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Interfaz de entrada (In Port) que define el contrato del caso de uso para CreateMunicipio. Permite a la interfaz de usuario invocar operaciones del sistema sin conocer su implementación interna.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CreateMunicipioUseCase
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public interface CreateMunicipioUseCase {
    MunicipioModel execute(CreateMunicipioCommand command);
}


