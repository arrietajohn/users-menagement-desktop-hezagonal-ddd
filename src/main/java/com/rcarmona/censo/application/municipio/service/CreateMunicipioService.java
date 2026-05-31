package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.CreateMunicipioUseCase;
import com.rcarmona.censo.application.municipio.port.out.SaveMunicipioPort;
import com.rcarmona.censo.application.municipio.service.dto.command.CreateMunicipioCommand;
import com.rcarmona.censo.application.municipio.service.mapper.MunicipioApplicationMapper;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicaciÃ³n para crear un Municipio.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de CreateMunicipio, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CreateMunicipioService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class CreateMunicipioService implements CreateMunicipioUseCase {

    private final SaveMunicipioPort saveMunicipioPort;

    @Override
    public MunicipioModel execute(CreateMunicipioCommand command) {
        MunicipioModel municipio = MunicipioApplicationMapper.toDomain(command);
        return saveMunicipioPort.save(municipio);
    }
}


