package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findById(Long id);
    Optional<Room> findByRoomNumber(String roomNumber);
    List<Room> findAll();
    List<Room> findAvailableRooms(); // aqui tenemos el caso de uso diferente en la Unidsd 3
    void update(Room room);
    void deleteById(Long id);
}