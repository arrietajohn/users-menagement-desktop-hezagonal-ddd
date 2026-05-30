package co.edu.udc.desechos_fabrica.user.application.port.in;

import co.edu.udc.desechos_fabrica.user.application.service.dto.query.GetUserByEmailQuery;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;

public interface GetUserByEmailUseCase {
  UserModel execute(GetUserByEmailQuery query);
}
