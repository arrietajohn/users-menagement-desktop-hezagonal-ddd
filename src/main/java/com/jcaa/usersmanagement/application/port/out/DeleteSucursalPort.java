package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;

public interface DeleteSucursalPort {
    void delete(SucursaId id);
}
