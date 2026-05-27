package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;

public interface UpdateRangoMilitarPort {
    RangoMilitarModel update(RangoMilitarModel rango);
}