package com.jcaa.usersmanagement.domain.model.nino.vo;

public enum EstadoInscripcion {

    ACTIVO,
    BAJA;

    public boolean isActivo() {
        return this == ACTIVO;
    }

    public boolean isBaja() {
        return this == BAJA;
    }
}
