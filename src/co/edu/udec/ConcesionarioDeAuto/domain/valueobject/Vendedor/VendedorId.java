package edu.udec.ConcesionarioDeAuto.domain.valueobject.Vendedor;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;
import java.util.Objects;

public record VendedorId(String value) {

    public VendedorId {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        if (valorNormalizado.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }

        value = valorNormalizado;
    }
}