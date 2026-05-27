package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.RangoId;

public interface DeleteRangoMilitarPort {
    void delete(RangoId id);
}
