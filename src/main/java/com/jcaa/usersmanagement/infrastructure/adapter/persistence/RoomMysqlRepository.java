package com.jcaa.usersmanagement.infrastructure.adapter.persistence;

import com.jcaa.usersmanagement.application.port.out.RoomRepository;
import com.jcaa.usersmanagement.domain.model.Room;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.RoomEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.RoomPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomMysqlRepository implements RoomRepository {

    private final Connection connection;


    public RoomMysqlRepository(Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public Room save(Room room) {
        String query = "INSERT INTO rooms (room_number, type, price_per_night, is_available, hotel_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getType());
            statement.setDouble(3, room.getPricePerNight());
            statement.setBoolean(4, room.isAvailable());
            statement.setLong(5, room.getHotelId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving room into database", e);
        }
        return room;
    }

    @Override
    public Optional<Room> findById(Long id) {
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(RoomPersistenceMapper.toDomain(mapResultSetToEntity(resultSet)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding room by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Room> findByRoomNumber(String roomNumber) {
        String query = "SELECT * FROM rooms WHERE room_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(RoomPersistenceMapper.toDomain(mapResultSetToEntity(resultSet)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding room by room number", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                rooms.add(RoomPersistenceMapper.toDomain(mapResultSetToEntity(resultSet)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing all rooms", e);
        }
        return rooms;
    }

    @Override
    public List<Room> findAvailableRooms() {
        // Lógica diferenciada para la Unidad 3: Filtrado directo en SQL usando WHERE
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE is_available = true";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                rooms.add(RoomPersistenceMapper.toDomain(mapResultSetToEntity(resultSet)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing available rooms", e);
        }
        return rooms;
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE rooms SET room_number = ?, type = ?, price_per_night = ?, is_available = ?, hotel_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getType());
            statement.setDouble(3, room.getPricePerNight());
            statement.setBoolean(4, room.isAvailable());
            statement.setLong(5, room.getHotelId());
            statement.setLong(6, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating room details", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting room from database", e);
        }
    }

    // Método auxiliar reutilizable para evitar duplicar código de mapeo de ResultSet
    private RoomEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new RoomEntity(
                resultSet.getLong("id"),
                resultSet.getString("room_number"),
                resultSet.getString("type"),
                resultSet.getDouble("price_per_night"),
                resultSet.getBoolean("is_available"),
                resultSet.getLong("hotel_id")
        );
    }
}
