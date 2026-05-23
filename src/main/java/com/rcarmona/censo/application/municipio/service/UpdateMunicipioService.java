package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.UpdateMunicipioUseCase;
import com.rcarmona.censo.application.municipio.port.out.UpdateMunicipioPort;
import com.rcarmona.censo.application.municipio.port.out.GetMunicipioByIdPort;
import com.rcarmona.censo.application.municipio.service.dto.command.UpdateMunicipioCommand;
import com.rcarmona.censo.application.municipio.service.mapper.MunicipioApplicationMapper;
import com.rcarmona.censo.domain.municipio.exception.MunicipioNotFoundException;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicaciÃ³n para actualizar un Municipio.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de UpdateMunicipio, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: UpdateMunicipioService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class UpdateMunicipioService implements UpdateMunicipioUseCase {

    private final UpdateMunicipioPort updateMunicipioPort;
    private final GetMunicipioByIdPort getMunicipioByIdPort;

    @Override
    public MunicipioModel execute(UpdateMunicipioCommand command) {
        MunicipioId id = new MunicipioId(command.id());
        getMunicipioByIdPort.findById(id)
                .orElseThrow(() -> new MunicipioNotFoundException(command.id()));

        MunicipioModel municipio = MunicipioApplicationMapper.toDomain(command);
        return updateMunicipioPort.update(municipio);
    }
}


