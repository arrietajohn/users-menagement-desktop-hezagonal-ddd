package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import java.util.List;

public interface GetAllUsersUseCase {
  List<UserModel> execute();
}
