package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.SucursalModel;

public interface SaveSucursalPort {
    SucursalModel save(SucursalModel sucursalModel);
}
