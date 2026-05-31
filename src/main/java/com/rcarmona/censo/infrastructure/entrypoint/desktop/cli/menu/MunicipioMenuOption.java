package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Arrays;
import java.util.Optional;

/**
 * Opciones del menÃº interactivo para el Municipio.
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
 * - Enumeración (Enum) que define de forma estructurada y estandarizada todas las opciones disponibles dentro del menú de consola que el usuario puede elegir.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioMenuOption
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public enum MunicipioMenuOption {
    CREATE(1, "Crear Municipio"),
    UPDATE(2, "Actualizar Municipio"),
    DELETE(3, "Eliminar Municipio"),
    FIND_BY_ID(4, "Buscar Municipio por ID"),
    LIST_ALL(5, "Listar Todos los Municipios"),
    COUNT(6, "Contar Municipios"),
    FIND_BY_PROVINCIA(7, "Filtrar por Provincia"),
    SEARCH_BY_NAME(8, "Buscar por Nombre"),
    EXIT(9, "Regresar al MenÃº Principal");

    private final int number;
    private final String description;

    MunicipioMenuOption(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<MunicipioMenuOption> fromNumber(int number) {
        return Arrays.stream(values())
                .filter(option -> option.getNumber() == number)
                .findFirst();
    }
}


