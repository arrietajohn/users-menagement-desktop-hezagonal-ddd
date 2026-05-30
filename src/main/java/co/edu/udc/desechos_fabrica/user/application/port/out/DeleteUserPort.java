package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;

public interface DeleteUserPort {
  void delete(UserEmail userEmail);
}
