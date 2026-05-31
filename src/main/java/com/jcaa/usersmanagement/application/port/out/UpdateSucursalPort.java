package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;

public interface UpdateSucursalPort {
    SucursalModel update(SucursalModel sucursalToUpdate);
}
