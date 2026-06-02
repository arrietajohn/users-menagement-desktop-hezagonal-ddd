package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteDocumentoCommand;

public interface DeleteDocumentoUseCase {
  void execute(DeleteDocumentoCommand command);
}
