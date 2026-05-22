package com.rcarmona.censo.application.municipio.service;

import com.rcarmona.censo.application.municipio.port.in.GetAllMunicipiosUseCase;
import com.rcarmona.censo.application.municipio.port.out.GetAllMunicipiosPort;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Servicio de aplicaciÃ³n para listar todos los Municipios.
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
 * - Implementación concreta del caso de uso. Orquesta el flujo de trabajo de GetAllMunicipios, recibe instrucciones, interactúa con el Modelo de Dominio y delega la persistencia a los puertos de salida correspondientes.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: GetAllMunicipiosService
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class GetAllMunicipiosService implements GetAllMunicipiosUseCase {

    private final GetAllMunicipiosPort getAllMunicipiosPort;

    @Override
    public List<MunicipioModel> execute() {
        return getAllMunicipiosPort.findAll();
    }
}


