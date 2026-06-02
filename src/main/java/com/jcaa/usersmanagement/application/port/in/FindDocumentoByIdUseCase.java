package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.FindDocumentoByIdQuery;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;

public interface FindDocumentoByIdUseCase {
  DocumentoModel execute(FindDocumentoByIdQuery query);
}
