package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRoomCommand;
import com.jcaa.usersmanagement.domain.model.Room;

public class RoomApplicationMapper {


    public static Room toDomain(CreateRoomCommand command) {
        if (command == null) return null;

        Room room = new Room();
        room.setRoomNumber(command.roomNumber());
        room.setType(command.type());
        room.setPricePerNight(command.pricePerNight());
        room.setHotelId(command.hotelId());
        return room;
    }

    // en esta parte concvertimos los datos (update) que usaremos adelante
    public static Room toDomainFromUpdate(Long id, String roomNumber, String type, double price, boolean isAvailable, Long hotelId) {
        Room room = new Room();
        room.setId(id);
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setPricePerNight(price);
        room.setAvailable(isAvailable);
        room.setHotelId(hotelId);
        return room;
    }
}
