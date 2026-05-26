package com.rcarmona.censo.infrastructure.entrypoint.desktop.controller;

import com.rcarmona.censo.application.municipio.port.in.*;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.handler.*;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.menu.MunicipioMenuOption;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;

import java.util.EnumMap;
import java.util.Map;

/**
 * Orquestador CLI del mÃ³dulo Municipio.
 * Refactorizado para usar el mismo patrÃ³n de CLI Handlers de UserManagement.
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Punto central de entrada (Primary Adapter). Actúa como fachada y enrutador maestro. Recibe las llamadas del menú interactivo o consola y las redirige hacia el Caso de Uso (Service) adecuado.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioController
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class MunicipioController {

    private final Map<MunicipioMenuOption, MunicipioOperationHandler> handlers;
    private final CensoConsoleIO console;

    public MunicipioController(
            CreateMunicipioUseCase createUseCase,
            UpdateMunicipioUseCase updateUseCase,
            DeleteMunicipioUseCase deleteUseCase,
            GetMunicipioByIdUseCase getByIdUseCase,
            GetAllMunicipiosUseCase getAllUseCase,
            CountMunicipiosUseCase countUseCase,
            FindMunicipiosByProvinciaUseCase findByProvUseCase,
            SearchMunicipiosByNameUseCase searchByNameUseCase,
            CensoConsoleIO console
    ) {
        this.console = console;
        this.handlers = new EnumMap<>(MunicipioMenuOption.class);
        
        handlers.put(MunicipioMenuOption.CREATE, new CreateMunicipioHandler(createUseCase, console));
        handlers.put(MunicipioMenuOption.UPDATE, new UpdateMunicipioHandler(updateUseCase, console));
        handlers.put(MunicipioMenuOption.DELETE, new DeleteMunicipioHandler(deleteUseCase, console));
        handlers.put(MunicipioMenuOption.FIND_BY_ID, new FindMunicipioByIdHandler(getByIdUseCase, console));
        handlers.put(MunicipioMenuOption.LIST_ALL, new ListMunicipiosHandler(getAllUseCase, console));
        handlers.put(MunicipioMenuOption.COUNT, new CountMunicipiosHandler(countUseCase, console));
        handlers.put(MunicipioMenuOption.FIND_BY_PROVINCIA, new FindMunicipiosByProvinciaHandler(findByProvUseCase, console));
        handlers.put(MunicipioMenuOption.SEARCH_BY_NAME, new SearchMunicipiosByNameHandler(searchByNameUseCase, console));
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            console.println("\n=== MENÃš MUNICIPIO ===");
            for (MunicipioMenuOption option : MunicipioMenuOption.values()) {
                console.println(option.getNumber() + ". " + option.getDescription());
            }

            int choice = console.readInt("Seleccione una opciÃ³n: ");
            MunicipioMenuOption option = MunicipioMenuOption.fromNumber(choice).orElse(null);

            if (option == MunicipioMenuOption.EXIT) {
                exit = true;
                continue;
            }

            if (option != null && handlers.containsKey(option)) {
                try {
                    handlers.get(option).handle();
                } catch (Exception e) {
                    console.println("Error: " + e.getMessage());
                }
            } else {
                console.println("OpciÃ³n invÃ¡lida.");
            }
        }
    }
}


