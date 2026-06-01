package edu.udec.ConcesionarioDeAuto.domain.valueobject.Mecanico;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record MecanicoEspecialidad(String value) {

    private static final int LONGITUD_MAXIMA = 20;

    public MecanicoEspecialidad {

        final String valorNormalizado =
                Objects.requireNonNull(value, "La especialidad no puede ser nula")
                        .trim();

        validarVacio(valorNormalizado);
        validarLongitud(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }

    private static void validarLongitud(final String valor) {

        if (valor.length() > LONGITUD_MAXIMA) {
            throw CamposInvalidosExcepcion.camposInvalidos();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}

