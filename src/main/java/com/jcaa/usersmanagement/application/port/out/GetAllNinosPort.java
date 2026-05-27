package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.nino.Nino;

import java.util.List;

public interface GetAllNinosPort {
    List<Nino> getAll();
}