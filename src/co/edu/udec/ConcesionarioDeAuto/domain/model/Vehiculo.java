package edu.udec.ConcesionarioDeAuto.domain.model;

import edu.udec.ConcesionarioDeAuto.domain.enums.EstadoVehiculo;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Vehiculo.VehiculoColor;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Vehiculo.VehiculoKilometraje;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Vehiculo.VehiculoPlaca;

import lombok.Value;

@Value
public class Vehiculo {

    VehiculoPlaca placa;
    VehiculoColor color;
    VehiculoKilometraje kilometraje;
    ModeloVehiculo modelo;
    EstadoVehiculo estado;

    public static Vehiculo nuevo(
            final VehiculoPlaca placa,
            final VehiculoColor color,
            final VehiculoKilometraje kilometraje,
            final ModeloVehiculo modelo
    ) {
        return new Vehiculo(
                placa,
                color,
                kilometraje,
                modelo,
                EstadoVehiculo.NUEVO
        );
    }

    public static Vehiculo usado(
            final VehiculoPlaca placa,
            final VehiculoColor color,
            final VehiculoKilometraje kilometraje,
            final ModeloVehiculo modelo
    ) {
        return new Vehiculo(
                placa,
                color,
                kilometraje,
                modelo,
                EstadoVehiculo.USADO
        );
    }
    public static Vehiculo enVenta(
            final VehiculoPlaca placa,
            final VehiculoColor color,
            final VehiculoKilometraje kilometraje,
            final ModeloVehiculo modelo
    ) {
        return new Vehiculo(
                placa,
                color,
                kilometraje,
                modelo,
                EstadoVehiculo.EN_VENTA
        );
    }
}

