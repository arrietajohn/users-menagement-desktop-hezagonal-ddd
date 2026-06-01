package edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ClienteSegundoNombre(String value) {

    public ClienteSegundoNombre {

        final String valorNormalizado =
                Objects.requireNonNull(value, "El nombre no puede ser nulo").trim();

        validarNombre(valorNormalizado);
        value = valorNormalizado;
    }

    private static void validarNombre(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
