package edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ClienteApellido(String value) {

    public ClienteApellido {

        final String valorNormalizado =
                Objects.requireNonNull(value, "El apellido no puede ser nulo").trim();

        validarApellido(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarApellido(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}