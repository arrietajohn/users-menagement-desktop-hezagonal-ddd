package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.doctor.DeleteDoctorPort;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.application.port.out.doctor.SaveDoctorPort;
import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.DoctorPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.DoctorPersistenceMapper;
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
public final class DoctorRepositoryMySQL implements SaveDoctorPort, GetDoctorPort, DeleteDoctorPort {

    private static final String SQL_INSERT =
            "INSERT INTO doctors "
                    + "(id, name, address, phone, population, province, postal_code, nif, social_security_number, medical_license, category, status, created_at, updated_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE doctors SET name = ?, address = ?, phone = ?, population = ?, province = ?, postal_code = ?, nif = ?, social_security_number = ?, medical_license = ?, category = ?, status = ?, updated_at = NOW() "
                    + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, address, phone, population, province, postal_code, nif, social_security_number, medical_license, category, status, created_at, updated_at "
                    + "FROM doctors "
                    + "WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_BY_NIF =
            "SELECT id, name, address, phone, population, province, postal_code, nif, social_security_number, medical_license, category, status, created_at, updated_at "
                    + "FROM doctors "
                    + "WHERE nif = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, address, phone, population, province, postal_code, nif, social_security_number, medical_license, category, status, created_at, updated_at "
                    + "FROM doctors "
                    + "ORDER BY name ASC";

    private static final String SQL_DELETE =
            "DELETE FROM doctors "
                    + "WHERE id = ?";

    private final Connection connection;

    @Override
    public DoctorModel save(final DoctorModel doctor) {
        final DoctorPersistenceDto dto = DoctorPersistenceMapper.fromModelToDto(doctor);
        if (findById(doctor.getId()).isPresent()) {
            executeUpdate(dto);
        } else {
            executeSave(dto);
        }
        return findByIdOrFail(doctor.getId());
    }

    @Override
    public Optional<DoctorModel> findById(final String id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(DoctorPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al buscar médico por ID: " + id, exception);
        }
    }

    @Override
    public Optional<DoctorModel> findByNif(final String nif) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_NIF)) {
            statement.setString(1, nif);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(DoctorPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al buscar médico por NIF: " + nif, exception);
        }
    }

    @Override
    public List<DoctorModel> findAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return DoctorPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al listar todos los médicos", exception);
        }
    }

    @Override
    public void deleteById(final String id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al eliminar el médico con ID: " + id, exception);
        }
    }

    private void executeSave(final DoctorPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.name());
            statement.setString(3, dto.address());
            statement.setString(4, dto.phone());
            statement.setString(5, dto.population());
            statement.setString(6, dto.province());
            statement.setString(7, dto.postalCode());
            statement.setString(8, dto.nif());
            statement.setString(9, dto.socialSecurityNumber());
            statement.setString(10, dto.medicalLicense());
            statement.setString(11, dto.category());
            statement.setString(12, dto.status());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al insertar el registro del médico con ID: " + dto.id(), exception);
        }
    }

    private void executeUpdate(final DoctorPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.name());
            statement.setString(2, dto.address());
            statement.setString(3, dto.phone());
            statement.setString(4, dto.population());
            statement.setString(5, dto.province());
            statement.setString(6, dto.postalCode());
            statement.setString(7, dto.nif());
            statement.setString(8, dto.socialSecurityNumber());
            statement.setString(9, dto.medicalLicense());
            statement.setString(10, dto.category());
            statement.setString(12, dto.id());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al actualizar el registro del médico con ID: " + dto.id(), exception);
        }
    }

    private DoctorModel findByIdOrFail(final String id) {
        return findById(id)
                .orElseThrow(() -> new DoctorValidationException("Médico no encontrado con el ID: " + id));
    }
}