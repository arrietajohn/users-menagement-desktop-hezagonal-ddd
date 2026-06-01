package edu.udec.ConcesionarioDeAuto.domain.exceptions;

import com.jcaa.usersmanagement.domain.exception.DomainException;

public final class CamposInvalidosExcepcion extends DomainException {

    private static final String MESSAGE_CAMPOS_INVALIDOS = "Los campos deben ser del mismo formato";
    private static final String MESSAGE_CAMPOS_OBLIGATORIOS = "Debe llenar este campo";
    private static final String MESSAGE_CAMPOS_LIMITE = "El campo excede el limite de lo requerido";

    private CamposInvalidosExcepcion(final String message) {
        super(message);
    }

    public static CamposInvalidosExcepcion camposInvalidos() {
        return new CamposInvalidosExcepcion(MESSAGE_CAMPOS_INVALIDOS);
    }

    public static CamposInvalidosExcepcion camposObligatoriosVacios() {
        return new CamposInvalidosExcepcion(MESSAGE_CAMPOS_OBLIGATORIOS);
    }

    public static CamposInvalidosExcepcion camposObligatoriosLimite() {
        return new CamposInvalidosExcepcion(MESSAGE_CAMPOS_LIMITE);
    }
}