package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.nino.Nino;

public interface SaveNinoPort {

    Nino save(Nino nino);

}