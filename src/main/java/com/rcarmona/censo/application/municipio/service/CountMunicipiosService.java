package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.CountMunicipiosUseCase;
import com.rcarmona.censo.application.municipio.port.out.CountMunicipiosPort;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicaciÃ³n para contar Municipios.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de CountMunicipios, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CountMunicipiosService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class CountMunicipiosService implements CountMunicipiosUseCase {

    private final CountMunicipiosPort countMunicipiosPort;

    @Override
    public long execute() {
        return countMunicipiosPort.count();
    }
}


