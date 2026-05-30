package com.rcarmona.censo.infrastructure.adapter.persistence.municipio.repository;

import com.rcarmona.censo.application.municipio.port.out.*;
import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import com.rcarmona.censo.infrastructure.adapter.persistence.municipio.entity.MunicipioEntity;
import com.rcarmona.censo.infrastructure.adapter.persistence.municipio.mapper.MunicipioPersistenceMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de Persistencia (Driven Adapter) para MySQL.
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Adaptador de Infraestructura físico. Implementa los puertos de salida para interactuar directamente con el motor de base de datos MySQL/PostgreSQL, ejecutando consultas SQL de persistencia y lectura para MunicipioRepositoryMySQL.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioRepositoryMySQL
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MunicipioRepositoryMySQL implements 
    SaveMunicipioPort, UpdateMunicipioPort, DeleteMunicipioPort, 
    GetMunicipioByIdPort, GetAllMunicipiosPort, CountMunicipiosPort, 
    FindMunicipiosByProvinciaPort, SearchMunicipiosByNamePort {

    private final Connection connection;

    public MunicipioRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MunicipioModel save(MunicipioModel municipio) {
        String sql = "INSERT INTO municipio (nombre_municipio, id_provincia) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            MunicipioEntity entity = MunicipioPersistenceMapper.toEntity(municipio);
            stmt.setString(1, entity.getNombre());
            stmt.setInt(2, entity.getProvinciaId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return findById(new MunicipioId(rs.getInt(1))).orElseThrow();
            }
            throw new RuntimeException("No se generÃ³ el ID en BD");
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar municipio: " + e.getMessage(), e);
        }
    }

    @Override
    public MunicipioModel update(MunicipioModel municipio) {
        String sql = "UPDATE municipio SET nombre_municipio = ?, id_provincia = ? WHERE id_municipio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            MunicipioEntity entity = MunicipioPersistenceMapper.toEntity(municipio);
            stmt.setString(1, entity.getNombre());
            stmt.setInt(2, entity.getProvinciaId());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
            return findById(municipio.getId()).orElseThrow();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar municipio: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(MunicipioId id) {
        String sql = "DELETE FROM municipio WHERE id_municipio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id.value());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar municipio: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<MunicipioModel> findById(MunicipioId id) {
        String sql = "SELECT id_municipio, nombre_municipio, id_provincia FROM municipio WHERE id_municipio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id.value());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MunicipioEntity entity = MunicipioPersistenceMapper.extractFromResultSet(rs);
                    return Optional.of(MunicipioPersistenceMapper.toDomain(entity));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar municipio: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<MunicipioModel> findAll() {
        String sql = "SELECT id_municipio, nombre_municipio, id_provincia FROM municipio";
        List<MunicipioModel> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MunicipioEntity entity = MunicipioPersistenceMapper.extractFromResultSet(rs);
                list.add(MunicipioPersistenceMapper.toDomain(entity));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar municipios: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM municipio";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar municipios: " + e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public List<MunicipioModel> findByProvinciaId(Integer provinciaId) {
        String sql = "SELECT id_municipio, nombre_municipio, id_provincia FROM municipio WHERE id_provincia = ?";
        List<MunicipioModel> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, provinciaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MunicipioEntity entity = MunicipioPersistenceMapper.extractFromResultSet(rs);
                    list.add(MunicipioPersistenceMapper.toDomain(entity));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por provincia: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<MunicipioModel> searchByName(String partialName) {
        String sql = "SELECT id_municipio, nombre_municipio, id_provincia FROM municipio WHERE nombre_municipio LIKE ?";
        List<MunicipioModel> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + partialName + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MunicipioEntity entity = MunicipioPersistenceMapper.extractFromResultSet(rs);
                    list.add(MunicipioPersistenceMapper.toDomain(entity));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por nombre: " + e.getMessage(), e);
        }
        return list;
    }
}


