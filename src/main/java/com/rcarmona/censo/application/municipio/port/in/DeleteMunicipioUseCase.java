package com.rcarmona.censo.application.municipio.port.in;

/**
 * Caso de Uso: Eliminar Municipio.
 * 
 * @author Rosary Carmona
 */
import com.rcarmona.censo.application.municipio.service.dto.command.DeleteMunicipioCommand;
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Interfaz de entrada (In Port) que define el contrato del caso de uso para DeleteMunicipio. Permite a la interfaz de usuario invocar operaciones del sistema sin conocer su implementación interna.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: DeleteMunicipioUseCase
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public interface DeleteMunicipioUseCase {
    void execute(DeleteMunicipioCommand command);
}


