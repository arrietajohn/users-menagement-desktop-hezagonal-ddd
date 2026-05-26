package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.CreateProgramaCommand;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
public interface CreateProgramaUseCase {
  ProgramaModel execute(CreateProgramaCommand command);
}
