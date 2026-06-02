package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.DocumentoModel;

public interface UpdateDocumentoPort {
  DocumentoModel update(DocumentoModel documento);
}
