package com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Propia implementaciÃ³n de ConsoleIO para el mÃ³dulo de Censo,
 * asegurando aislamiento total de los paquetes del profesor.
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Adaptador de consola de bajo nivel. Centraliza y estandariza todas las lecturas de teclado (Input) y escrituras en pantalla (Output) manejando errores de formato tipográfico del usuario.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CensoConsoleIO
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class CensoConsoleIO {

  private final Scanner scanner;
  private final PrintStream out;

  public CensoConsoleIO(Scanner scanner, PrintStream out) {
      this.scanner = scanner;
      this.out = out;
  }

  public String readRequired(final String prompt) {
    String value;
    do {
      out.print(prompt);
      value = scanner.nextLine().trim();
      if (value.isBlank()) {
        out.println("  El valor no puede estar vacÃ­o. IntÃ©ntalo de nuevo.");
      }
    } while (value.isBlank());
    return value;
  }

  public String readOptional(final String prompt) {
    out.print(prompt);
    return scanner.nextLine().trim();
  }

  public int readInt(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim();
      try {
        return Integer.parseInt(raw);
      } catch (final NumberFormatException ignored) {
        out.println("  Entrada invÃ¡lida. Por favor, ingresa un nÃºmero.");
      }
    }
  }

  public void println(final String message) {
    out.println(message);
  }

  public void println() {
    out.println();
  }

  public void printf(final String format, final Object... args) {
    out.printf(format, args);
  }
}


