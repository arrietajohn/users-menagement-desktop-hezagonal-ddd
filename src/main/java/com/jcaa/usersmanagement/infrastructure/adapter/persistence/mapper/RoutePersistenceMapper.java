package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.RouteModel;
import com.jcaa.usersmanagement.domain.valueobject.RouteId;
import com.jcaa.usersmanagement.domain.valueobject.RouteName;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.RoutePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.RouteEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RoutePersistenceMapper {

    public RoutePersistenceDto fromModelToDto(final RouteModel route) {
        return new RoutePersistenceDto(
                route.getId().value(),
                route.getName().value(),
                route.getNeighborhood(),
                route.getSchool(),
                route.getJourney(),
                route.getMaxCapacity(),
                route.getAvailableSeats(),
                route.getActive(),
                null,
                null);
    }

    public RouteEntity fromResultSetToEntity(final ResultSet resultSet)
            throws SQLException {

        return new RouteEntity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("neighborhood"),
                resultSet.getString("school"),
                resultSet.getString("journey"),
                resultSet.getInt("max_capacity"),
                resultSet.getInt("available_seats"),
                resultSet.getBoolean("active"),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at"));
    }

    public RouteModel fromEntityToModel(final RouteEntity entity) {
        return new RouteModel(
                new RouteId(entity.id()),
                new RouteName(entity.name()),
                entity.neighborhood(),
                entity.school(),
                entity.journey(),
                entity.maxCapacity(),
                entity.availableSeats(),
                entity.active());
    }

    public RouteModel fromResultSetToModel(final ResultSet resultSet)
            throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<RouteModel> fromResultSetToModelList(final ResultSet resultSet)
            throws SQLException {

        final List<RouteModel> routes = new ArrayList<>();

        while (resultSet.next()) {
            routes.add(fromResultSetToModel(resultSet));
        }

        return routes;
    }
}