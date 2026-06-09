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
  CREATE_SESSION(7, "Create session"),
  LIST_SESSIONS(8, "List all sessions"),
  FIND_SESSION(9, "Find session by ID"),
  FIND_SESSION_BY_DATE(10, "Buscar Sessiones por fecha"),
  FIND_SESSION_BY_CHAIRMAN(11,"Buscar sessiones por chairman"),
  LIST_SESSION_ORDERED_BY_DATE (12,"Listar Sessiones Ordenadas por fecha y hora"),
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

