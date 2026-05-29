package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAvailableRoomsUseCase;
import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.model.Room;
import java.util.List;

public class GetAvailableRoomsService implements GetAvailableRoomsUseCase {

    private final RoomRepository roomRepository;

    public GetAvailableRoomsService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAvailableRooms() {
        return roomRepository.findAvailableRooms();
    }
}