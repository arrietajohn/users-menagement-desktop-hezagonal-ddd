package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.nino.Nino;

public interface UpdateNinoPort {
    Nino update(Nino nino);
}