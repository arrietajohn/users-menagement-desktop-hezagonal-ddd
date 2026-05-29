package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.Room;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.RoomEntity;

public class RoomPersistenceMapper {

    public static RoomEntity toEntity(Room domain) {
        if (domain == null) return null;
        return new RoomEntity(
                domain.getId(),
                domain.getRoomNumber(),
                domain.getType(),
                domain.getPricePerNight(),
                domain.isAvailable(),
                domain.getHotelId()
        );
    }

    public static Room toDomain(RoomEntity entity) {
        if (entity == null) return null;
        return new Room(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getType(),
                entity.getPricePerNight(),
                entity.isAvailable(),
                entity.getHotelId()
        );
    }
}
