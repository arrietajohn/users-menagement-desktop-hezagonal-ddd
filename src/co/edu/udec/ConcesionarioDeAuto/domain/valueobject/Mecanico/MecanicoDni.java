package edu.udec.ConcesionarioDeAuto.domain.valueobject.Mecanico;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.DniInvalidoExcepcion;

import java.util.Objects;

public record MecanicoDni(String value) {

    public MecanicoDni {
        final String valorNormalizado =
                Objects.requireNonNull(value, "El DNI no puede ser nulo").trim();
        validarVacio(valorNormalizado);
        validarFormato(valorNormalizado);
        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {
        if (valor.isEmpty()) {
            throw DniInvalidoExcepcion.dniNulo();
        }
    }

    private static void validarFormato(final String valor) {

        if (!valor.matches("\\d{1,10}")) {
            throw DniInvalidoExcepcion.dniInvalido();
        }
    }

    @Override
    public String toString() {
        return value;
    }


}