package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;

import java.util.Optional;

public interface GetDocumentoByIdPort {
  Optional<DocumentoModel> getById(DocumentoId id);
}
