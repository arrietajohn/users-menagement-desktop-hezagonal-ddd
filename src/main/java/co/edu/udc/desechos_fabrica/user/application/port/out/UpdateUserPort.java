package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;

public interface UpdateUserPort {
  UserModel update(UserModel user);
}
