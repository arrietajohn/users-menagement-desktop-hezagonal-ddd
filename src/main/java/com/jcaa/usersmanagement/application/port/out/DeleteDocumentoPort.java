package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;

public interface DeleteDocumentoPort {
  void delete(DocumentoId id);
}
