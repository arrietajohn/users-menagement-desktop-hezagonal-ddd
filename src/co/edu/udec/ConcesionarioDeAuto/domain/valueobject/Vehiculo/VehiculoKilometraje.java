package edu.udec.ConcesionarioDeAuto.domain.valueobject.Vehiculo;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.KilometrajeInvalidoExcepcion;


public record VehiculoKilometraje(Integer value) {

    public VehiculoKilometraje {

        validarNulo(value);
        validarKilometraje(value);
    }

    private static void validarNulo(final Integer valor) {

        if (valor == null) {
            throw KilometrajeInvalidoExcepcion.kilometrajeNulo();
        }
    }

    private static void validarKilometraje(final Integer valor) {

        if (valor < 0) {
            throw KilometrajeInvalidoExcepcion.kilometrajeInvalido();
        }
    }
}