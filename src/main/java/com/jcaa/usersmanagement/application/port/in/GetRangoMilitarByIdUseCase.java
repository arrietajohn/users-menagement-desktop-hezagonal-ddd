package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetRangoMilitarByIdQuery;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;

public interface GetRangoMilitarByIdUseCase {
    RangoMilitarModel execute(GetRangoMilitarByIdQuery query);
}