package edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ClienteTelefono(String value) {

    public ClienteTelefono {

        final String valorNormalizado =
                Objects.requireNonNull(value, "El teléfono no puede ser nulo").trim();

        validarTelefono(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarTelefono(final String valor) {

        if (!valor.matches("\\d{10}")) {
            throw CamposInvalidosExcepcion.camposInvalidos();
        }
    }

    @Override
    public String toString() {
        return value;
    }

}