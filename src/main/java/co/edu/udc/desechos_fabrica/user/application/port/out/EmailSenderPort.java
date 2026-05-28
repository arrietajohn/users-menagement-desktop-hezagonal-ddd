package co.edu.udc.desechos_fabrica.user.application.port.out;

import co.edu.udc.desechos_fabrica.user.domain.model.EmailDestinationModel;

public interface EmailSenderPort {
  void send(EmailDestinationModel destination);
}
