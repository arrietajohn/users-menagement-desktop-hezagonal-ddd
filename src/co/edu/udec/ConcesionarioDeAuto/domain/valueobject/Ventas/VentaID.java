package edu.udec.ConcesionarioDeAuto.domain.valueobject.Venta;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record VentaID(String value) {

    public VentaID {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        if (valorNormalizado.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }

        value = valorNormalizado;
    }
}