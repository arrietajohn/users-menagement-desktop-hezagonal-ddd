package edu.udec.ConcesionarioDeAuto.domain.valueobject.ModeloVehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CilindrajeInvalidoExcepcion;
import java.util.Objects;

public record ModeloVehiculoCilindraje(String value) {

    public ModeloVehiculoCilindraje {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim();

        validarVacio(valorNormalizado);
        validarFormato(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw CilindrajeInvalidoExcepcion.cilindrajeNulo();
        }
    }

    private static void validarFormato(final String valor) {

        if (!valor.matches("\\d+")) {
            throw CilindrajeInvalidoExcepcion.cilindrajeInvalido();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
