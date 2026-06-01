package edu.udec.ConcesionarioDeAuto.domain.valueobject.OpcionAdicional;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record OpcionAdicionalId(String value) {

    public OpcionAdicionalId {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        validar(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validar(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}