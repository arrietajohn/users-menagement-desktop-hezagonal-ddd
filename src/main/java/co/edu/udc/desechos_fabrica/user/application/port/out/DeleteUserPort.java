package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserId;

public interface DeleteUserPort {
  void delete(UserId userId);
}
