package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidTiempoMinimoAscensoException;

public record TiempoMinimoAscenso(int meses) {

    public TiempoMinimoAscenso {
        if (meses < 0) {
            throw InvalidTiempoMinimoAscensoException.becauseValueIsNegative(meses);
        }
    }

    @Override
    public String toString() {
        return meses + " meses";
    }
}