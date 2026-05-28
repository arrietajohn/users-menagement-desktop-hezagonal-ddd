package co.edu.udc.desechos_fabrica.user.application.service;

import co.edu.udc.desechos_fabrica.user.application.port.in.GetAllUsersUseCase;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetAllUsersPort;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllUsersService implements GetAllUsersUseCase {

  private final GetAllUsersPort getAllUsersPort;

  @Override
  public List<UserModel> execute() {
    return getAllUsersPort.getAll();
  }
}
