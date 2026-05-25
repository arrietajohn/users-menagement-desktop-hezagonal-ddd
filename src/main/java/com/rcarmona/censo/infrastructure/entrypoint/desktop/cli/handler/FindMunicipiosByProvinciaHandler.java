package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler;

import com.rcarmona.censo.application.municipio.port.in.FindMunicipiosByProvinciaUseCase;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.mapper.MunicipioDesktopMapper;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler para buscar Municipios por provincia.
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
 * - Controlador interactivo de consola. Intercepta la acción de FindMunicipiosByProvincia, solicita la entrada de datos al usuario de forma amigable, ensambla la petición y delega la ejecución al Controlador Principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: FindMunicipiosByProvinciaHandler
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class FindMunicipiosByProvinciaHandler implements MunicipioOperationHandler {

    private final FindMunicipiosByProvinciaUseCase useCase;
    private final CensoConsoleIO console;

    @Override
    public void handle() {
        console.println("\n--- Buscar por Provincia ---");
        Integer provId = console.readInt("ID de la Provincia: ");
        
        List<MunicipioModel> models = useCase.execute(provId);
        
        List<MunicipioResponse> responses = models.stream()
                .map(MunicipioDesktopMapper::toResponse)
                .collect(Collectors.toList());

        if (responses.isEmpty()) {
            console.println("No se encontraron municipios para esa provincia.");
        } else {
            responses.forEach(r -> console.println(r.toString()));
        }
    }
}


