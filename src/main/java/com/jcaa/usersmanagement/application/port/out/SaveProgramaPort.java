package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
public interface SaveProgramaPort {
  ProgramaModel save(ProgramaModel programa);
}
