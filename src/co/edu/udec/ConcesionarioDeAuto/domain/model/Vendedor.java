package edu.udec.ConcesionarioDeAuto.domain.model;

import edu.udec.ConcesionarioDeAuto.domain.enums.VendedorEstadoLaboral;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Vendedor.*;

import lombok.Value;

@Value
public class Vendedor {

    VendedorId id;
    VendedorNombre nombre;
    VendedorApellido apellido;
    VendedorEstadoLaboral estado;

    public static Vendedor activo(
            final VendedorId id,
            final VendedorNombre nombre,
            final VendedorApellido apellido,
            final  VendedorEstadoLaboral estado){

        return new Vendedor(
                id,
                nombre,
                apellido,
                estado.ACTIVO
        );
    }

    public static Vendedor inactivo(
            final VendedorId id,
            final VendedorNombre nombre,
            final VendedorApellido apellido,
            final  VendedorEstadoLaboral estado){
        return new Vendedor(
                id,
                nombre,
                apellido,
                estado.INACTIVO
        );
    }
}