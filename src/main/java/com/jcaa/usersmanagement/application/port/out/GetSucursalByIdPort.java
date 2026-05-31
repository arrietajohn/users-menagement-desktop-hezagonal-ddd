package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import java.util.Optional;

public interface GetSucursalByIdPort {
    Optional<SucursalModel> getById(SucursaId sucursaId);
}
