package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler;

import com.rcarmona.censo.application.municipio.port.in.DeleteMunicipioUseCase;
import com.rcarmona.censo.application.municipio.service.dto.command.DeleteMunicipioCommand;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import lombok.RequiredArgsConstructor;

/**
 * Handler para eliminar un Municipio.
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
 * - Controlador interactivo de consola. Intercepta la acción de DeleteMunicipio, solicita la entrada de datos al usuario de forma amigable, ensambla la petición y delega la ejecución al Controlador Principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: DeleteMunicipioHandler
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class DeleteMunicipioHandler implements MunicipioOperationHandler {

    private final DeleteMunicipioUseCase useCase;
    private final CensoConsoleIO console;

    @Override
    public void handle() {
        console.println("\n--- Eliminar Municipio ---");
        Integer id = console.readInt("ID del municipio a eliminar: ");
        
        useCase.execute(new DeleteMunicipioCommand(id));
        console.println("Municipio eliminado exitosamente.");
    }
}


