package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;

public interface UpdateUserPort {
  UserModel update(UserEmail currentEmail, UserModel user);
}
