package edu.udec.ConcesionarioDeAuto.domain.valueobject.Venta;


import edu.udec.ConcesionarioDeAuto.domain.exceptions.DescuentoInvalidoExcepcion;

public record VentaDescuento(Integer value) {

    public VentaDescuento {

        validarNulo(value);
        validarRango(value);
    }

    private static void validarNulo(final Integer valor) {

        if (valor == null) {
            throw DescuentoInvalidoExcepcion.descuentoNulo();
        }
    }

    private static void validarRango(final Integer valor) {

        if (valor < 0 || valor > 100) {
            throw DescuentoInvalidoExcepcion.descuentoInvalido();
        }
    }
}