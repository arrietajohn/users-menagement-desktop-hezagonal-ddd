package edu.udec.ConcesionarioDeAuto.domain.valueobject.ModeloVehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CamposInvalidosExcepcion;

import java.util.Objects;

public record ModeloVehiculoId(String value) {

    public ModeloVehiculoId {

        final String valorNormalizado =
                Objects.requireNonNull(value, "El id del modelo no puede ser nulo")
                        .trim();

        validarVacio(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw CamposInvalidosExcepcion.camposObligatoriosVacios();
        }
    }
}