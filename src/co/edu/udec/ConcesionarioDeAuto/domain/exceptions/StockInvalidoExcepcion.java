package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public class StockInvalidoExcepcion extends RuntimeException {
  public StockInvalidoExcepcion(String message) {
    super(message);
  }
}
