package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import java.util.List;
public interface GetAllProgramasUseCase {
  List<ProgramaModel> execute();
}
