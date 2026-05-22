package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.GetMunicipioByIdUseCase;
import com.rcarmona.censo.application.municipio.port.out.GetMunicipioByIdPort;
import com.rcarmona.censo.domain.municipio.exception.MunicipioNotFoundException;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicaciÃ³n para obtener un Municipio por ID.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de GetMunicipioById, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: GetMunicipioByIdService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class GetMunicipioByIdService implements GetMunicipioByIdUseCase {

    private final GetMunicipioByIdPort getMunicipioByIdPort;

    @Override
    public MunicipioModel execute(Integer id) {
        return getMunicipioByIdPort.findById(new MunicipioId(id))
                .orElseThrow(() -> new MunicipioNotFoundException(id));
    }
}


