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
  LIST_ESPECIES(7, "Listar especies"),
  FIND_ESPECIE(8, "Buscar especie por ID"),
  CREATE_ESPECIE(9, "Registrar especie"),
  UPDATE_ESPECIE(10, "Actualizar especie"),
  DELETE_ESPECIE(11, "Eliminar especie"),
  REGISTRAR_EXAMEN(12, "Registrar Examen Médico"),
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