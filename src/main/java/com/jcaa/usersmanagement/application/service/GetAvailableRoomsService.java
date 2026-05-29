package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListAvailableRoomsUseCase;
import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.model.Room;
import java.util.List;

public class GetAvailableRoomsService implements ListAvailableRoomsUseCase {

    private final RoomRepository roomRepository;

    public GetAvailableRoomsService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> execute() {
        // invocamos el puerto de salida aplicando el filtro de disponibilidad en la persistencia
        return roomRepository.findAvailableRooms();
    }
}
