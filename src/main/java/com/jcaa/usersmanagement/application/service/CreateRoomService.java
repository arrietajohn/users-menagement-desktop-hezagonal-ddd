package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateRoomUseCase;
import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.model.Room;

public class CreateRoomService implements CreateRoomUseCase {

    private final RoomRepository roomRepository;

    // inyección por constructor (cumplemos SOLID: invertimos dependencias
    public CreateRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room execute(Room room) {
        // regla de negocio: validamos si el número de habitación ya existe en el repositorio
        roomRepository.findByRoomNumber(room.getRoomNumber()).ifPresent(r -> {
            throw new IllegalArgumentException("Room number already exists in this hotel.");
        });

        room.setAvailable(true); // Toda habitación registrada por primera debería estar disponible por defecto
        return roomRepository.save(room);
    }
}
