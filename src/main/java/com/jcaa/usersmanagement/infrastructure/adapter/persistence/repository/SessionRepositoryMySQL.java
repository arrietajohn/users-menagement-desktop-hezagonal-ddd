package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.SessionId;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.SessionPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.SessionPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class SessionRepositoryMySQL
        implements SaveSessionPort, GetAllSessionsPort, GetSessionByIdPort, GetSessionByDatePort, GetSessionByChairmanPort,
                    GetSessionOrderedByDatePort, GetSessionByDateAndSalaPort {
    private static final String SQL_SELECT_ALL =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, " +
                    "ID_Charman, Fecha, Hora_Inicio, Hora_Fin FROM sessions";


    private static final String SQL_INSERT =
            "INSERT INTO sessions "
                    + "(ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, ID_Charman," +
                    " Fecha, Hora_Inicio, Hora_Fin) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

    private static final String SQL_SELECT_BY_ID =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, ID_Charman, Fecha, Hora_Inicio, Hora_Fin "
                    + "FROM sessions "
                    + "WHERE ID_Sesion = ? LIMIT 1";

    private static final String SQL_SELECT_BY_DATE =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, " +
                    "ID_Charman, Fecha, Hora_Inicio, Hora_Fin FROM sessions " +
                    "WHERE Fecha = ?";

    private static final String SQL_SELECT_BY_CHAIRMAN =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, " +
                    "ID_Charman, Fecha, Hora_Inicio, Hora_Fin FROM sessions " +
                    "WHERE ID_Charman = ?";

    private static final String SQL_SELECT_BY_ORDERED_BY_DATE =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, " +
                    "ID_Charman, Fecha, Hora_Inicio, Hora_Fin FROM sessions " +
                    "ORDER BY Fecha, Hora_Inicio";

    private static final String SQL_SELECT_BY_DATE_AND_SALA =
            "SELECT ID_Sesion, ID_Sala, ID_Investigacion, ID_Ponenete, " +
                    "ID_Charman, Fecha, Hora_Inicio, Hora_Fin FROM sessions " +
                    "WHERE Fecha = ? AND ID_Sala = ?";

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

    @Override
    public List<Session> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return SessionPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public Optional<Session> getById(final SessionId sessionId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, sessionId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(SessionPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(sessionId.value(), exception);
        }
    }

    @Override
    public List<Session> getByDate(String fecha) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DATE)) {
            statement.setString(1, fecha);
            final ResultSet resultSet = statement.executeQuery();
            return SessionPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public List<Session> getByChairman(String chairmanId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CHAIRMAN)) {
            statement.setString(1, chairmanId);
            final ResultSet resultSet = statement.executeQuery();
            return SessionPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public List<Session> getOrderedByDate() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ORDERED_BY_DATE)) {
            final ResultSet resultSet = statement.executeQuery();
            return SessionPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public  List<Session> getByDateAndSala (String fecha, String salaId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DATE_AND_SALA)){
            statement.setString(1, fecha);
            statement.setString(2, salaId);
            final ResultSet resultSet = statement.executeQuery();
            return SessionPersistenceMapper.fromResultSetToModelList(resultSet);
        }  catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }
}

