package edu.udec.ConcesionarioDeAuto.domain.valueobject.Venta;


import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record VentaLugarEntrega(String value) {

    private static final int LONGITUD_MAXIMA = 150;

    public VentaLugarEntrega {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        validar(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validar(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }

        if (valor.length() > LONGITUD_MAXIMA) {
            throw CamposInvalidosExcepcion.camposInvalidos();
        }
    }
}