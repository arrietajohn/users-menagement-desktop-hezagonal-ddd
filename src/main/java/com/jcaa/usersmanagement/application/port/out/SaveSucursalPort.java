package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.UserModel;

public interface SaveSucursalPort {
    UserModel save(UserModel user);
}
