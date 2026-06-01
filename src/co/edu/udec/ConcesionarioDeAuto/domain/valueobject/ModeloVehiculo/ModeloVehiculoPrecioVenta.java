package edu.udec.ConcesionarioDeAuto.domain.valueobject.ModeloVehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.PrecioInvalidoExcepcion;

import java.math.BigDecimal;
import java.util.Objects;

public record ModeloVehiculoPrecioVenta(BigDecimal value) {

    public ModeloVehiculoPrecioVenta {

        if (value == null) {
            throw PrecioInvalidoExcepcion.precioNulo();
        }
        validarPrecio(value);
    }

    private static void validarPrecio(final BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw PrecioInvalidoExcepcion.precioInvalido();
        }
    }
}

