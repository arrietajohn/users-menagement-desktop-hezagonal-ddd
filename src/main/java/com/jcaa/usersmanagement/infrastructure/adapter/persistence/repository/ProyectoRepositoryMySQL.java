package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.domain.model.Proyecto;
import com.jcaa.usersmanagement.domain.model.ProyectoRepository;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class ProyectoRepositoryMySQL implements ProyectoRepository {

    private final Connection connection;

    @Override
    public Proyecto save(Proyecto proyecto) {
        String sql = "INSERT INTO proyectos (nombre_clave, denominacion, fecha_inicio, fecha_fin, estado, id_promotor) VALUES (?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, proyecto.getNombreClave());
            ps.setString(2, proyecto.getDenominacion());
            ps.setDate(3, Date.valueOf(proyecto.getFechaInicio()));
            ps.setDate(4, Date.valueOf(proyecto.getFechaFin()));
            ps.setString(5, proyecto.getEstado());
            ps.setLong(6, proyecto.getIdPromotor());
            ps.executeUpdate();

            try (final ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    proyecto.setIdProyecto(generatedKeys.getLong(1));
                }
            }
            return proyecto;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al guardar el proyecto en la base de datos", exception);
        }
    }

    @Override
    public Optional<Proyecto> findById(Long id) {
        String sql = "SELECT * FROM proyectos WHERE id_proyecto = ? LIMIT 1";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProyecto(rs));
                }
            }
            return Optional.empty();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al buscar el proyecto por ID", exception);
        }
    }

    @Override
    public List<Proyecto> findAll() {
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos ORDER BY denominacion ASC";
        try (final PreparedStatement ps = connection.prepareStatement(sql);
             final ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                proyectos.add(mapResultSetToProyecto(rs));
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al listar todos los proyectos", exception);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM proyectos WHERE id_proyecto = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al eliminar el proyecto", exception);
        }
    }

    @Override
    public void updateEstado(Long idProyecto, String nuevoEstado) {
        String sql = "UPDATE proyectos SET estado = ? WHERE id_proyecto = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setLong(2, idProyecto);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al actualizar el estado del proyecto", exception);
        }
    }

    @Override
    public void updateFechaFin(Long idProyecto, LocalDate nuevaFechaFin) {
        String sql = "UPDATE proyectos SET fecha_fin = ? WHERE id_proyecto = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(nuevaFechaFin));
            ps.setLong(2, idProyecto);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al prorrogar la fecha fin del proyecto", exception);
        }
    }

    @Override
    public void updatePromotor(Long idProyecto, Long nuevoIdPromotor) {
        String sql = "UPDATE proyectos SET id_promotor = ? WHERE id_proyecto = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, nuevoIdPromotor);
            ps.setLong(2, idProyecto);
            ps.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al cambiar el promotor del proyecto", exception);
        }
    }

    //  IMPLEMENTACIÓN DE CONSULTAS AVANZADAS - UNIDAD 4


    @Override
    public List<Proyecto> findProyectosEnCurso() {
        // Consulta CEA 1: Proyectos con estado operativo Activo o En Curso
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos WHERE estado = 'ACTIVO' OR estado = 'EN_CURSO' ORDER BY id_proyecto DESC";
        try (final PreparedStatement ps = connection.prepareStatement(sql);
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                proyectos.add(mapResultSetToProyecto(rs));
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al consultar proyectos en curso de la empresa", exception);
        }
    }

    @Override
    public List<Proyecto> findProyectosCompletadosPorRangoFechas(LocalDate inicio, LocalDate fin) {
        // Consulta CEA 7: Proyectos finalizados filtrados transaccionalmente por un rango de fechas
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos WHERE (estado = 'FINALIZADO' OR estado = 'COMPLETADO') AND fecha_fin BETWEEN ? AND ? ORDER BY fecha_fin ASC";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(inicio));
            ps.setDate(2, Date.valueOf(fin));
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proyectos.add(mapResultSetToProyecto(rs));
                }
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al consultar proyectos completados por rango de fechas", exception);
        }
    }

    @Override
    public List<Proyecto> findByPromotorId(Long idPromotor) {
        // Consulta CEA Adaptada 3: Carga de proyectos asignados a un líder específico
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos WHERE id_promotor = ? ORDER BY fecha_inicio DESC";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idPromotor);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proyectos.add(mapResultSetToProyecto(rs));
                }
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al obtener proyectos del promotor con ID: " + idPromotor, exception);
        }
    }

    @Override
    public List<Proyecto> findByEstadoEspecifico(String estado) {
        // Consulta CEA Adaptada 4: Filtrado dinámico por cualquier estado
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos WHERE estado = ? ORDER BY denominacion ASC";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, estado);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proyectos.add(mapResultSetToProyecto(rs));
                }
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al filtrar portafolio por estado: " + estado, exception);
        }
    }

    @Override
    public List<Proyecto> findByDenominacionLike(String termino) {
        // Consulta CEA Adaptada 5: Coincidencia parcial de texto en la denominación comercial
        final List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyectos WHERE denominacion LIKE ? ORDER BY denominacion ASC";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + termino + "%");
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proyectos.add(mapResultSetToProyecto(rs));
                }
            }
            return proyectos;
        } catch (final SQLException exception) {
            throw new RuntimeException("Error al buscar proyectos por patrón de denominación", exception);
        }
    }


    private Proyecto mapResultSetToProyecto(final ResultSet rs) throws SQLException {
        final Proyecto p = new Proyecto();
        p.setIdProyecto(rs.getLong("id_proyecto"));
        p.setNombreClave(rs.getString("nombre_clave"));
        p.setDenominacion(rs.getString("denominacion"));
        p.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        p.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        p.setEstado(rs.getString("estado"));
        p.setIdPromotor(rs.getLong("id_promotor"));
        return p;
    }
}