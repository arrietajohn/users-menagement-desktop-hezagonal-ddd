package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import java.util.Optional;

public interface GetUserByIdPort {
  Optional<UserModel> getById(UserId userId);
}
