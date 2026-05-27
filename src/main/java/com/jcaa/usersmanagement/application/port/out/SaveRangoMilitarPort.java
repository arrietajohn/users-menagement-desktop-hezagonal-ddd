package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;

public interface SaveRangoMilitarPort {
    RangoMilitarModel save(RangoMilitarModel rango);