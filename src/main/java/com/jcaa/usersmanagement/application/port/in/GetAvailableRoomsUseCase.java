package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Room;
import java.util.List;

public interface GetAvailableRoomsUseCase {
    List<Room> getAvailableRooms();
}
