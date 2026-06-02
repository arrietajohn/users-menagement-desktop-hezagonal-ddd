package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateDocumentoCommand;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;

public interface UpdateDocumentoUseCase {
  DocumentoModel execute(UpdateDocumentoCommand command);
}
