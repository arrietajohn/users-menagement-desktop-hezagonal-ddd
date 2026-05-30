package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.SessionPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.SessionPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log
@RequiredArgsConstructor
public final class SessionRepositoryMySQL
        implements SaveSessionPort
{


    private static final String SQL_INSERT =
            "INSERT INTO Session "
                    + "(ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, ID_Charman," +
                    " Fecha, Hora_Inicio, Hora_Fin) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

    private final Connection connection;

    @Override
    public Session save(final Session session) {
        final SessionPersistenceDto dto = SessionPersistenceMapper.fromModelToDto(session);
        executeSave(dto);
        return session;

    }
    private void executeSave(final SessionPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.salaId());
            statement.setString(3, dto.investigacionId());
            statement.setString(4, dto.ponenteId());
            statement.setString(5, dto.chairmanId());
            statement.setString(6, dto.fecha());
            statement.setString(7, dto.horaInicio());
            statement.setString(8, dto.horaFin());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed(dto.id(), exception);
        }
    }
    }

