package edu.udec.ConcesionarioDeAuto.domain.valueobject.Venta;


import edu.udec.ConcesionarioDeAuto.domain.exceptions.FechaEntregaInvalidaExcepcion;

import java.time.LocalDate;

public record VentaFechaEntrega(LocalDate value) {

    public VentaFechaEntrega {

        if (value == null) {
            throw FechaEntregaInvalidaExcepcion.fechaEntregaNula();
        }
    }
}