package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import java.util.Optional;

public interface GetRangoMilitarByIdPort {
    Optional<RangoMilitarModel> getById(RangoId id);
}