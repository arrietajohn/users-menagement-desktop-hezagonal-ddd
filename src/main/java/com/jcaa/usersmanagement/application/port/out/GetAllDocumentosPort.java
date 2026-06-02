package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.DocumentoModel;

import java.util.List;

public interface GetAllDocumentosPort {
  List<DocumentoModel> getAll();
}
