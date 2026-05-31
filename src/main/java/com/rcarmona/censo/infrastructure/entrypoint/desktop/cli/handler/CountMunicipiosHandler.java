package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler;

import com.rcarmona.censo.application.municipio.port.in.CountMunicipiosUseCase;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import lombok.RequiredArgsConstructor;

/**
 * Handler para contar Municipios.
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
 * - Controlador interactivo de consola. Intercepta la acción de CountMunicipios, solicita la entrada de datos al usuario de forma amigable, ensambla la petición y delega la ejecución al Controlador Principal.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CountMunicipiosHandler
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class CountMunicipiosHandler implements MunicipioOperationHandler {

    private final CountMunicipiosUseCase useCase;
    private final CensoConsoleIO console;

    @Override
    public void handle() {
        console.println("\n--- Contar Municipios ---");
        long count = useCase.execute();
        console.println("Total de municipios registrados: " + count);
    }
}


