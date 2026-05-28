package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserId;
import java.util.Optional;

public interface GetUserByIdPort {
  Optional<UserModel> getById(UserId userId);
}
