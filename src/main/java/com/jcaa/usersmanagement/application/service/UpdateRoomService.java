package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateRoomUseCase;
import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.exception.RoomNotFoundException;
import com.jcaa.usersmanagement.domain.model.Room;

public class UpdateRoomService implements UpdateRoomUseCase {

    private final RoomRepository roomRepository;

    public UpdateRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void execute(Room room) {
        // tenemos una regla de negocio obligatoria: validar que exista la habitación (room) antes de modificar
        roomRepository.findById(room.getId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + room.getId()));

        roomRepository.update(room);
    }
}
