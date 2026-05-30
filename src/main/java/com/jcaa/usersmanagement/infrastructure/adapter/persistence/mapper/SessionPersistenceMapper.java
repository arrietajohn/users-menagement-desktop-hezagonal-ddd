package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.SessionPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.UserPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.SessionEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SessionPersistenceMapper {

    public SessionPersistenceDto fromModelToDto(final Session session) {
        return new SessionPersistenceDto(
                session.getId().value(),
                session.getSalaID().value(),
                session.getInvestigacionId().value(),
                session.getPonenteId().value(),
                session.getChairmanId().value(),
                session.getFecha().toString(),
                session.getHoraInicio().toString(),
                session.getHoraFin().toString());

    }

    public SessionEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new SessionEntity(
                resultSet.getString("ID_Sesion"),
                resultSet.getString("ID_Sala"),
                resultSet.getString("ID_Investigacion"),
                resultSet.getString("ID_Ponente"),
                resultSet.getString("ID_Charman"),
                resultSet.getString("Fecha"),
                resultSet.getString("Hora_Sesion"),
                resultSet.getString("Hora_Fin"));
    }

    public Session fromEntityToModel(final SessionEntity entity) {
        return new Session(
                new SessionId(entity.id()),
                new SalaID(entity.salaId()),
                new InvestigacionId(entity.investigacionId()),
                new UserId(entity.ponenteId()),
                new UserId(entity.chairmanId()),
                LocalDate.parse(entity.fecha()),
                LocalTime.parse(entity.horaInicio()),
                LocalTime.parse(entity.horaFin()));
    }

    public Session fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<Session> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<Session> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(fromResultSetToModel(resultSet));
        }
        return users;
    }
}