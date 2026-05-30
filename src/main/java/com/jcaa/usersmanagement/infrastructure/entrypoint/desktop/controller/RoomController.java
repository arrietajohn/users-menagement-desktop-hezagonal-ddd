package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateRoomUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteRoomUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAvailableRoomsUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateRoomUseCase;
import com.jcaa.usersmanagement.domain.model.Room;

import java.util.List;

public class RoomController {

    private final CreateRoomUseCase createRoomUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;
    private final GetAvailableRoomsUseCase getAvailableRoomsUseCase;

    public RoomController(
            CreateRoomUseCase createRoomUseCase,
            UpdateRoomUseCase updateRoomUseCase,
            DeleteRoomUseCase deleteRoomUseCase,
            GetAvailableRoomsUseCase getAvailableRoomsUseCase) {
        this.createRoomUseCase = createRoomUseCase;
        this.updateRoomUseCase = updateRoomUseCase;
        this.deleteRoomUseCase = deleteRoomUseCase;
        this.getAvailableRoomsUseCase = getAvailableRoomsUseCase;
    }

    public Room createRoom(String roomNumber, String type, double pricePerNight, Long hotelId) {
        Room room = new Room(null, roomNumber, type, pricePerNight, true, hotelId);
        return createRoomUseCase.execute(room);
    }

    public void updateRoom(Long id, String roomNumber, String type, double pricePerNight, boolean isAvailable, Long hotelId) {
        Room room = new Room(id, roomNumber, type, pricePerNight, isAvailable, hotelId);
        updateRoomUseCase.execute(room);
    }

    public void deleteRoom(Long id) {
        deleteRoomUseCase.execute(id);
    }

    public List<Room> getAvailableRooms() {
        return getAvailableRoomsUseCase.getAvailableRooms();
    }
}
