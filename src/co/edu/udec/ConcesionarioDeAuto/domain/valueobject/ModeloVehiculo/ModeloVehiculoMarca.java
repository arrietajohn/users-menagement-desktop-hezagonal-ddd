package edu.udec.ConcesionarioDeAuto.domain.valueobject.ModeloVehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ModeloVehiculoMarca(String value) {

    private static final int LONGITUD_MAXIMA = 50;

    public ModeloVehiculoMarca {

        final String valorNormalizado =
                Objects.requireNonNull(value, "La marca no puede ser nula")
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