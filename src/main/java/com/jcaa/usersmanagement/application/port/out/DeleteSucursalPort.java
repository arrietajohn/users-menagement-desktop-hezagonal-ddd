package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.valueobject.SucursalNumber;

public interface DeleteSucursalPort {
    void delete(SucursalNumber sucursalNumber);
}
