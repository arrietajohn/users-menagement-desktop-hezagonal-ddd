package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import java.util.List;

public interface GetAllUsersPort {
  List<UserModel> getAll();
}
