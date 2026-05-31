package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.SearchMunicipiosByNameUseCase;
import com.rcarmona.censo.application.municipio.port.out.SearchMunicipiosByNamePort;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Servicio de aplicaciÃ³n para buscar por nombre.
 * 
 * @author Rosary Carmona
 */
@RequiredArgsConstructor
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de SearchMunicipiosByName, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: SearchMunicipiosByNameService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class SearchMunicipiosByNameService implements SearchMunicipiosByNameUseCase {

    private final SearchMunicipiosByNamePort searchMunicipiosByNamePort;

    @Override
    public List<MunicipioModel> execute(String partialName) {
        return searchMunicipiosByNamePort.searchByName(partialName);
    }
}


