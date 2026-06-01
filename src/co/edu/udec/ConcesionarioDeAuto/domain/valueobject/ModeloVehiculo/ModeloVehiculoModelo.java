package edu.udec.ConcesionarioDeAuto.domain.valueobject.ModeloVehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ModeloVehiculoModelo(String value) {

    private static final int LONGITUD_MAXIMA = 50;

    public ModeloVehiculoModelo {

        final String valorNormalizado =
                Objects.requireNonNull(value, "El modelo no puede ser nulo")
                        .trim();

        validar(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validar(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }

        if (valor.length() > LONGITUD_MAXIMA) {
            throw CamposInvalidosExcepcion.camposInvalidos();
        }
    }
}