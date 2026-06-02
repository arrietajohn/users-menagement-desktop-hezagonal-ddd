package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuOption {

  LIST_USERS(1, "List all users"),
  FIND_USER(2, "Find user by ID"),
  CREATE_USER(3, "Create user"),
  UPDATE_USER(4, "Update user"),
  DELETE_USER(5, "Delete user"),
  LOGIN(6, "Login"),
  LIST_PROYECTOS(7, "Listar proyectos"),
  FIND_PROYECTO(8, "Buscar proyecto por ID"),
  CREATE_PROYECTO(9, "Crear proyecto"),
  UPDATE_PROYECTO(10, "Actualizar proyecto"),
  DELETE_PROYECTO(11, "Eliminar proyecto"),
  LIST_EMPLEADOS(12, "Listar empleados"),
  FIND_EMPLEADO(13, "Buscar empleado por ID"),
  CREATE_EMPLEADO(14, "Crear empleado"),
  UPDATE_EMPLEADO(15, "Actualizar empleado"),
  DELETE_EMPLEADO(16, "Eliminar empleado"),
  LIST_TAREAS(17, "Listar tareas"),
  FIND_TAREA(18, "Buscar tarea por ID"),
  CREATE_TAREA(19, "Crear tarea"),
  UPDATE_TAREA(20, "Actualizar tarea"),
  DELETE_TAREA(21, "Eliminar tarea"),
  LIST_DOCUMENTOS(22, "Listar documentos"),
  FIND_DOCUMENTO(23, "Buscar documento por ID"),
  CREATE_DOCUMENTO(24, "Crear documento"),
  UPDATE_DOCUMENTO(25, "Actualizar documento"),
  DELETE_DOCUMENTO(26, "Eliminar documento"),
  EXIT(0, "Exit");

  private final int number;
  private final String description;

  public static Optional<MenuOption> fromNumber(final int number) {
    for (final MenuOption option : values()) {
      if (option.number == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}

