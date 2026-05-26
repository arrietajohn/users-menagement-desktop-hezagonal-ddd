package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import java.util.List;
public interface GetAllProgramasPort {
  List<ProgramaModel> getAll();
}
