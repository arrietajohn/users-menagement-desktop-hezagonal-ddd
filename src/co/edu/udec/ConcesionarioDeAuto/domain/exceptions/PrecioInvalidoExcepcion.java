package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class PrecioInvalidoExcepcion extends DomainExceptionsCEA {

  private static final String MESSAGE_PRECIO_INVALIDO = "Debe introducir un precio válido.";
  private static final String MESSAGE_PRECIO_NULO = "El precio no puede ser nulo.";

  private PrecioInvalidoExcepcion(final String message) {
    super(message);
  }

  public static PrecioInvalidoExcepcion precioInvalido() {
    return new PrecioInvalidoExcepcion(MESSAGE_PRECIO_INVALIDO);
  }

  public static PrecioInvalidoExcepcion precioNulo() {
    return new PrecioInvalidoExcepcion(MESSAGE_PRECIO_NULO);
  }
}