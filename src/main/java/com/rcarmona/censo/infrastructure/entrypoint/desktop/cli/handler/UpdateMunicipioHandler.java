package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler;

import com.rcarmona.censo.application.municipio.port.in.UpdateMunicipioUseCase;
import com.rcarmona.censo.application.municipio.service.dto.command.UpdateMunicipioCommand;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.UpdateMunicipioRequest;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.mapper.MunicipioDesktopMapper;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import lombok.RequiredArgsConstructor;

/**
 * Handler para actualizar un Municipio.
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
 * - Controlador interactivo de consola. Intercepta la acción de UpdateMunicipio, solicita la entrada de datos al usuario de forma amigable, ensambla la petición y delega la ejecución al Controlador Principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: UpdateMunicipioHandler
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class UpdateMunicipioHandler implements MunicipioOperationHandler {

    private final UpdateMunicipioUseCase useCase;
    private final CensoConsoleIO console;

    @Override
    public void handle() {
        console.println("\n--- Actualizar Municipio ---");
        Integer id = console.readInt("ID del municipio a actualizar: ");
        String nombre = console.readRequired("Nuevo Nombre: ");
        Integer provId = console.readInt("Nuevo ID Provincia: ");

        UpdateMunicipioRequest request = new UpdateMunicipioRequest(id, nombre, provId);
        UpdateMunicipioCommand command = MunicipioDesktopMapper.toCommand(request);
        
        MunicipioModel model = useCase.execute(command);
        MunicipioResponse response = MunicipioDesktopMapper.toResponse(model);

        console.println("Municipio actualizado exitosamente: " + response);
    }
}


