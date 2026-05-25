package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler;

import com.rcarmona.censo.application.municipio.port.in.GetMunicipioByIdUseCase;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.mapper.MunicipioDesktopMapper;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import lombok.RequiredArgsConstructor;

/**
 * Handler para buscar un Municipio por ID.
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
 * - Controlador interactivo de consola. Intercepta la acción de FindMunicipioById, solicita la entrada de datos al usuario de forma amigable, ensambla la petición y delega la ejecución al Controlador Principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: FindMunicipioByIdHandler
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class FindMunicipioByIdHandler implements MunicipioOperationHandler {

    private final GetMunicipioByIdUseCase useCase;
    private final CensoConsoleIO console;

    @Override
    public void handle() {
        console.println("\n--- Buscar Municipio por ID ---");
        Integer id = console.readInt("ID: ");
        
        MunicipioModel model = useCase.execute(id);
        MunicipioResponse response = MunicipioDesktopMapper.toResponse(model);
        console.println("Municipio encontrado: " + response);
    }
}


