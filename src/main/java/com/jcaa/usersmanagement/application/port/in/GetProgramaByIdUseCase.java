package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.query.GetProgramaByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
public interface GetProgramaByIdUseCase {
  ProgramaModel execute(GetProgramaByIdQuery query);
}
