package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.domain.model.Room;

public interface CreateRoomUseCase {
    Room execute(Room room);
}
