package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
public interface DeleteProgramaPort {
  void deleteById(ProgramaId id);
}
