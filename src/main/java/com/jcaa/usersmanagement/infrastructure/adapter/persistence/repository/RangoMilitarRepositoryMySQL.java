package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteRangoMilitarPort;
import com.jcaa.usersmanagement.application.port.out.GetAllRangosMilitaresPort;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveRangoMilitarPort;
import com.jcaa.usersmanagement.application.port.out.UpdateRangoMilitarPort;
import com.jcaa.usersmanagement.domain.exception.RangoMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.RangoMilitarPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.RangoMilitarPersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.RangoMilitarPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class RangoMilitarRepositoryMySQL
        implements SaveRangoMilitarPort,
        UpdateRangoMilitarPort,
        DeleteRangoMilitarPort,
        GetRangoMilitarByIdPort,
        GetRangoMilitarByCodigoPort,
        GetAllRangosMilitaresPort {

    private static final String SQL_INSERT =
            "INSERT INTO rangos_militares (id, codigo, nombre, descripcion, linea_militar, tiempo_minimo_ascenso_meses) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE rangos_militares SET codigo = ?, nombre = ?, descripcion = ?, "
                    + "linea_militar = ?, tiempo_minimo_ascenso_meses = ? WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, codigo, nombre, descripcion, linea_militar, tiempo_minimo_ascenso_meses "
                    + "FROM rangos_militares WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_BY_CODIGO =
            "SELECT id, codigo, nombre, descripcion, linea_militar, tiempo_minimo_ascenso_meses "
                    + "FROM rangos_militares WHERE codigo = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, codigo, nombre, descripcion, linea_militar, tiempo_minimo_ascenso_meses "
                    + "FROM rangos_militares ORDER BY nombre ASC";

    private static final String SQL_DELETE =
            "DELETE FROM rangos_militares WHERE id = ?";

    private final Connection connection;

    @Override
    public RangoMilitarModel save(final RangoMilitarModel rango) {
        final RangoMilitarPersistenceDto dto = RangoMilitarPersistenceMapper.fromModelToDto(rango);
        executeSave(dto);
        return findByIdOrFail(rango.getId());
    }

    @Override
    public RangoMilitarModel update(final RangoMilitarModel rango) {
        final RangoMilitarPersistenceDto dto = RangoMilitarPersistenceMapper.fromModelToDto(rango);
        executeUpdate(dto);
        return findByIdOrFail(rango.getId());
    }

    @Override
    public Optional<RangoMilitarModel> getById(final RangoId id) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setString(1, id.value());
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(RangoMilitarPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseFindByIdFailed(id.value(), e);
        }
    }

    @Override
    public Optional<RangoMilitarModel> getByCodigo(final RangoCodigo codigo) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_CODIGO)) {
            st.setString(1, codigo.value());
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(RangoMilitarPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseFindByCodigoFailed(codigo.value(), e);
        }
    }

    @Override
    public List<RangoMilitarModel> getAll() {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = st.executeQuery();
            return RangoMilitarPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseFindAllFailed(e);
        }
    }

    @Override
    public void delete(final RangoId id) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_DELETE)) {
            st.setString(1, id.value());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseDeleteFailed(id.value(), e);
        }
    }

    private void executeSave(final RangoMilitarPersistenceDto dto) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_INSERT)) {
            st.setString(1, dto.id());
            st.setString(2, dto.codigo());
            st.setString(3, dto.nombre());
            st.setString(4, dto.descripcion());
            st.setString(5, dto.lineaMilitar());
            st.setInt(6, dto.tiempoMinimoAscensoMeses());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseSaveFailed(dto.id(), e);
        }
    }

    private void executeUpdate(final RangoMilitarPersistenceDto dto) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_UPDATE)) {
            st.setString(1, dto.codigo());
            st.setString(2, dto.nombre());
            st.setString(3, dto.descripcion());
            st.setString(4, dto.lineaMilitar());
            st.setInt(5, dto.tiempoMinimoAscensoMeses());
            st.setString(6, dto.id());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw RangoMilitarPersistenceException.becauseUpdateFailed(dto.id(), e);
        }
    }

    private RangoMilitarModel findByIdOrFail(final RangoId id) {
        return getById(id)
                .orElseThrow(() -> RangoMilitarNotFoundException.becauseIdWasNotFound(id.value()));
    }
}
