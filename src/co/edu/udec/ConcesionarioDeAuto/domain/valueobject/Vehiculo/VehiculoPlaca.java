package edu.udec.ConcesionarioDeAuto.domain.valueobject.Vehiculo;


import edu.udec.ConcesionarioDeAuto.domain.exceptions.PlacaInvalidaExcepcion;

import java.util.Objects;

public record VehiculoPlaca(String value) {

    public VehiculoPlaca {

        final String valorNormalizado =
                Objects.requireNonNull(value)
                        .trim()
                        .toUpperCase();

        validarVacio(valorNormalizado);
        validarFormato(valorNormalizado);
        validarExistencia(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw PlacaInvalidaExcepcion.becausePlacaIsNull();
        }
    }

    private static void validarFormato(final String valor) {

        if (!valor.matches("[A-Z0-9]{1,6}")) {
            throw PlacaInvalidaExcepcion.becausePlacaIsInvalid();
        }
    }
    private static void validarExistencia(final String valor) {

        if (valor.matches("[A-Z0-9]{1,6}")) {
            throw PlacaInvalidaExcepcion.becausePlacaIsExist();
        }

    }
}