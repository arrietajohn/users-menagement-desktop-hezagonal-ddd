package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuOption {

  LIST_USERS(1, "Listar todos los usuarios"),
  FIND_USER(2, "Buscar usuario por ID"),
  CREATE_USER(3, "Crear usuario"),
  UPDATE_USER(4, "Actualizar usuario"),
  DELETE_USER(5, "Eliminar usuario"),
  LOGIN(6, "Iniciar sesión"),
  EXIT(0, "Salir");

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

