package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.UserModel;
import java.util.List;

public interface GetAllUsersPort {
  List<UserModel> getAll();
}
