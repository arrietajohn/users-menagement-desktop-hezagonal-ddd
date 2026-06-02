package com.jcaa.usersmanagement.domain.exception;

public final class DocumentoNotFoundException extends DomainException {

  private DocumentoNotFoundException(final String message) {
    super(message);
  }

  public static DocumentoNotFoundException becauseIdWasNotFound(final Long documentoId) {
    return new DocumentoNotFoundException(
        String.format("El documento con id '%d' no fue encontrado.", documentoId));
  }
}
