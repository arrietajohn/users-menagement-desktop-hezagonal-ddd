package edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ClienteDireccion(String value) {

    public ClienteDireccion {

        final String valorNormalizado =
                Objects.requireNonNull(value, "La dirección no puede ser nula").trim();

        validarDireccion(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarDireccion(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}