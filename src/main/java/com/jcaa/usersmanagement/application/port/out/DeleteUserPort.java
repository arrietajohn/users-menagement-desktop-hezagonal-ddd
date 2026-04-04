package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.UserId;

public interface DeleteUserPort {
  void delete(UserId userId);
}
