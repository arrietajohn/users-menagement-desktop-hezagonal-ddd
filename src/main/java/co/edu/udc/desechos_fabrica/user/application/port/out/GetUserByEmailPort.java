package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import java.util.Optional;

public interface GetUserByEmailPort {
  Optional<UserModel> getByEmail(UserEmail email);
}
