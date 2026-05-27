package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import java.util.Optional;

public interface GetRangoMilitarByCodigoPort {
    Optional<RangoMilitarModel> getByCodigo(RangoCodigo codigo);
}