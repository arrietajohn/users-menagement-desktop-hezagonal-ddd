package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.EmailDestinationModel;

public interface EmailSenderPort {
  void send(EmailDestinationModel destination);
}
