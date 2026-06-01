package edu.udec.ConcesionarioDeAuto.domain.valueobject.Mecanico;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record MecanicoNombre(String value) {

    public MecanicoNombre {

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