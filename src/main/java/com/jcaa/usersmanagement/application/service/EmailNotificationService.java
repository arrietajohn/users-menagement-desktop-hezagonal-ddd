package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.EmailSenderPort;
import com.jcaa.usersmanagement.domain.exception.EmailSenderException;
import com.jcaa.usersmanagement.domain.model.EmailDestinationModel;
import com.jcaa.usersmanagement.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
public final class EmailNotificationService {

  private static final String SUBJECT_CREATED = "Tu cuenta ha sido creada — Gestión de Usuarios";
  private static final String SUBJECT_UPDATED =
      "Tu cuenta ha sido actualizada — Gestión de Usuarios";

  private final EmailSenderPort emailSenderPort;

  public void notifyUserCreated(final UserModel user, final String plainPassword) {
    final String template = loadTemplate("user-created.html");
    final String body = renderTemplate(template, Map.of(
        "name", user.getName().value(),
        "email", user.getEmail().value(),
        "password", plainPassword,
        "role", user.getRole().name()
    ));
    final EmailDestinationModel destination = buildDestination(user, SUBJECT_CREATED, body);
    sendOrLog(destination);
  }

  public void notifyUserUpdated(final UserModel user) {
    final String template = loadTemplate("user-updated.html");
    final String body = renderTemplate(template, Map.of(
        "name", user.getName().value(),
        "email", user.getEmail().value(),
        "role", user.getRole().name(),
        "status", user.getStatus().name()
    ));
    final EmailDestinationModel destination = buildDestination(user, SUBJECT_UPDATED, body);
    sendOrLog(destination);
  }

  private static EmailDestinationModel buildDestination(
      final UserModel user, final String subject, final String body) {
    return new EmailDestinationModel(user.getEmail().value(), user.getName().value(), subject, body);
  }

  private String loadTemplate(final String templateName) {
    final String path = "/templates/" + templateName;
    try (InputStream is = getClass().getResourceAsStream(path)) {
      if (is == null) {
        throw EmailSenderException.becauseSendFailed(new IllegalStateException("Template not found: " + path));
      }
      return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    } catch (final IOException e) {
      throw EmailSenderException.becauseSendFailed(e);
    }
  }

  private String renderTemplate(String template, final Map<String, String> values) {
    String result = template;
    for (final Map.Entry<String, String> entry : values.entrySet()) {
      final String token = "{{" + entry.getKey() + "}}";
      result = result.replace(token, entry.getValue() == null ? "" : entry.getValue());
    }
    return result;
  }

  private void sendOrLog(final EmailDestinationModel destination) {
    try {
      emailSenderPort.send(destination);
    } catch (final EmailSenderException exception) {
      log.log(
          Level.WARNING,
          "[EmailNotificationService] No se pudo enviar correo a: {0}. Causa: {1}",
          new Object[] {destination.getDestinationEmail(), exception.getMessage()});
      throw exception;
    }
  }
}
