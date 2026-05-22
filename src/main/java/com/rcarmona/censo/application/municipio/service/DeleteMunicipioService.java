package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.DeleteMunicipioUseCase;
import com.rcarmona.censo.application.municipio.port.out.DeleteMunicipioPort;
import com.rcarmona.censo.application.municipio.port.out.GetMunicipioByIdPort;
import com.rcarmona.censo.application.municipio.service.dto.command.DeleteMunicipioCommand;
import com.rcarmona.censo.domain.municipio.exception.MunicipioNotFoundException;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicaciÃ³n para eliminar un Municipio.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de DeleteMunicipio, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: DeleteMunicipioService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class DeleteMunicipioService implements DeleteMunicipioUseCase {

    private final DeleteMunicipioPort deleteMunicipioPort;
    private final GetMunicipioByIdPort getMunicipioByIdPort;

    @Override
    public void execute(DeleteMunicipioCommand command) {
        MunicipioId id = new MunicipioId(command.id());
        getMunicipioByIdPort.findById(id)
                .orElseThrow(() -> new MunicipioNotFoundException(command.id()));

        deleteMunicipioPort.delete(id);
    }
}


