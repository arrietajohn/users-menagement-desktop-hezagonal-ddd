package edu.udec.ConcesionarioDeAuto.domain.valueobject.Vendedor;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record VendedorNombre(String value) {

    public VendedorNombre {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        if (valorNormalizado.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }

        value = valorNormalizado;
    }
}