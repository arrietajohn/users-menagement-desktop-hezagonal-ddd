package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteRoomUseCase;
import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.exception.RoomNotFoundException;

public class DeleteRoomService implements DeleteRoomUseCase {

    private final RoomRepository roomRepository;

    public DeleteRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void execute(Long id) {
        // otra regla de negocio obligatoria: validar existencia antes de borrar
        roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));

        roomRepository.deleteById(id);
    }
}
