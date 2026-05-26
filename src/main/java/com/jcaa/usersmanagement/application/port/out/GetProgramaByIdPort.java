package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import java.util.Optional;
public interface GetProgramaByIdPort {
  Optional<ProgramaModel> getById(ProgramaId id);
}
