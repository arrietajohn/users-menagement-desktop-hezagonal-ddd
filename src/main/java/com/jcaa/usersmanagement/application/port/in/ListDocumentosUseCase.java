package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.DocumentoModel;

import java.util.List;

public interface ListDocumentosUseCase {
  List<DocumentoModel> execute();
}
