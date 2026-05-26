package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProgramaCommand;
public interface DeleteProgramaUseCase {
  void execute(DeleteProgramaCommand command);
}
