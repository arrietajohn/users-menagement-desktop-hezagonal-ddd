package com.rcarmona.censo.application.municipio.port.in;

import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import java.util.List;

/**
 * Caso de Uso Extra 3: BÃºsqueda Parcial de Municipios por Nombre.
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
 * - Interfaz de entrada (In Port) que define el contrato del caso de uso para SearchMunicipiosByName. Permite a la interfaz de usuario invocar operaciones del sistema sin conocer su implementación interna.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: SearchMunicipiosByNameUseCase
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public interface SearchMunicipiosByNameUseCase {
    List<MunicipioModel> execute(String partialName);
}


