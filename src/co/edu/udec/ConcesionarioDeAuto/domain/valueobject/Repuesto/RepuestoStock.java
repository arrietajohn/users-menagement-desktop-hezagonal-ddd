package edu.udec.ConcesionarioDeAuto.domain.valueobject.Repuesto;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.StockInvalidoExcepcion;

public record RepuestoStock(Integer value) {

    public RepuestoStock {

        if (value == null) {
            throw StockInvalidoExcepcion.stockNulo();
        }

        if (value < 0) {
            throw StockInvalidoExcepcion.stockInvalido();
        }
    }
}