package edu.udec.ConcesionarioDeAuto.domain.valueobject.Taller;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.NitInvalidoExcepcion;
import java.util.Objects;

public record TallerNit(String value) {

    public TallerNit {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        validarVacio(valorNormalizado);
        validarFormato(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw NitInvalidoExcepcion.nitNulo();
        }
    }

    private static void validarFormato(final String valor) {

        if (!valor.matches("\\d{1,11}")) {
            throw NitInvalidoExcepcion.nitInvalido();
        }
    }
}