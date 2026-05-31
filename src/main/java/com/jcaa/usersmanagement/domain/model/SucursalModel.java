package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.BancoId;
import com.jcaa.usersmanagement.domain.valueobject.SucursalCityOperation;
import com.jcaa.usersmanagement.domain.valueobject.SucursalPostalCode;
import com.jcaa.usersmanagement.domain.valueobject.SucursalDirection;
import com.jcaa.usersmanagement.domain.valueobject.NumeroSucursal;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import lombok.Value;

@Value //Crea constructor de todos los campos, getters, métodos ToString y hace los campos como final
public class SucursalModel {

    SucursaId id;
    NumeroSucursal numero;
    SucursalDirection direccion;
    SucursalPostalCode codigoPostal;
    SucursalCityOperation ciudad;
    BancoId bancoId;

    public static SucursalModel create(
            final SucursaId id,
            final NumeroSucursal numero,
            final SucursalDirection direccion,
            final SucursalPostalCode codigoPostal,
            final SucursalCityOperation ciudad,
            final BancoId bancoId) {
        return new SucursalModel(id, numero, direccion, codigoPostal, ciudad, bancoId);
    }
}