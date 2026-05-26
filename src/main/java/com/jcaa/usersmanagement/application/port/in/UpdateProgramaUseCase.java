package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProgramaCommand;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
public interface UpdateProgramaUseCase {
  ProgramaModel execute(UpdateProgramaCommand command);
}
