package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateDocumentoCommand;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;

public interface CreateDocumentoUseCase {
  DocumentoModel execute(CreateDocumentoCommand command);
}
