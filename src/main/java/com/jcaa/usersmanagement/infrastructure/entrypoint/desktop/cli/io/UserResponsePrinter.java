package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-10s : %s%n";

  private final ConsoleIO console;

  public void print(final UserResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",     response.id());
    console.printf(ROW_FORMAT, "Name",   response.name());
    console.printf(ROW_FORMAT, "Email",  response.email());
    console.printf(ROW_FORMAT, "Role",   response.role());
    console.printf(ROW_FORMAT, "Status", response.status());
    console.println(SEPARATOR);
  }

  public void printList(final List<UserResponse> users) {
    if (users.isEmpty()) {
      console.println("  No users found.");
      return;
    }
    console.printf("%n  Total: %d user(s)%n", users.size());
    users.forEach(this::print);
  }
}