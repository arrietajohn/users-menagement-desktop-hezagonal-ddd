package edu.udec.ConcesionarioDeAuto.domain.model;

import edu.udec.ConcesionarioDeAuto.domain.valueobject.Ventas.*;

public class Ventas {

    private final VentaID id;
    private VentaFechaCompra ventaFecha;
    private VentaDescuento descuento;
    private VentaFechaEntrega fechaEntrega;
    private VentaLugarEntrega lugarEntrega;

    public Ventas(
            VentaID id,
            VentaFechaCompra ventaFechaCompra,
            VentaDescuento ventaDescuento,
            VentaLugarEntrega lugarEntrega

    ) {


        this.id = id;
        this.ventaFecha = ventaFecha;
        this.descuento = descuento;
        this.lugarEntrega = lugarEntrega;
    }

}
