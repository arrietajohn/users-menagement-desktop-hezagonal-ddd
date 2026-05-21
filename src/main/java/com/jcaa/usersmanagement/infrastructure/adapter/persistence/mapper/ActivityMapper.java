package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.Activity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ActivityEntity;

public class ActivityMapper {

    // Convierte del Dominio a la Entidad de Base de Datos (Para Guardar/Actualizar)
    public static ActivityEntity toEntity(Activity domain) {
        if (domain == null) return null;
        return new ActivityEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getDayOfWeek(),
                domain.getSchedule(),
                domain.getPrice(),
                domain.isFree(),
                domain.getEmployeeId(),
                domain.getHotelId()
        );
    }

    // Convierte de la Entidad de Base de Datos al Dominio (Para Consultar/Listar)
    public static Activity toDomain(ActivityEntity entity) {
        if (entity == null) return null;
        return new Activity(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDayOfWeek(),
                entity.getSchedule(),
                entity.getPrice(),
                entity.isFree(),
                entity.getEmployeeId(),
                entity.getHotelId()
        );
    }
}

