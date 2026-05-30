package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.ActivityRepository;
import com.jcaa.usersmanagement.domain.model.Activity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ActivityRepositoryMySQL implements ActivityRepository {

    private static final String SQL_INSERT =
            "INSERT INTO activities " +
                    "(id, name, description, day_of_week, schedule, price, is_free, employee_id, hotel_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM activities WHERE id = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM activities";

    private static final String SQL_UPDATE =
            "UPDATE activities SET " +
                    "name = ?, description = ?, day_of_week = ?, schedule = ?, price = ?, is_free = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM activities WHERE id = ?";

    private final Connection connection;

    public ActivityRepositoryMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(final Activity activity) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, activity.getId());
            statement.setString(2, activity.getName());
            statement.setString(3, activity.getDescription());
            statement.setString(4, activity.getDayOfWeek());
            statement.setString(5, activity.getSchedule());
            statement.setBigDecimal(6, activity.getPrice());
            statement.setBoolean(7, activity.isFree());
            statement.setString(8, activity.getEmployeeId());
            statement.setInt(9, activity.getHotelId());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new RuntimeException(
                    "Error saving activity: " + exception.getMessage()
            );
        }
    }

    @Override
    public Optional<Activity> findById(final String id) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_BY_ID)) {

            statement.setString(1, id);

            final ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return Optional.of(mapResultSet(resultSet));
            }

            return Optional.empty();

        } catch (SQLException exception) {

            throw new RuntimeException(
                    "Error finding activity by id: " + exception.getMessage()
            );
        }
    }

    @Override
    public void update(final Activity activity) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, activity.getName());
            statement.setString(2, activity.getDescription());
            statement.setString(3, activity.getDayOfWeek());
            statement.setString(4, activity.getSchedule());
            statement.setBigDecimal(5, activity.getPrice());
            statement.setBoolean(6, activity.isFree());
            statement.setString(7, activity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new RuntimeException(
                    "Error updating activity: " + exception.getMessage()
            );
        }
    }

    @Override
    public void deleteById(final String id) {

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_DELETE)) {

            statement.setString(1, id);

            statement.executeUpdate();

        } catch (SQLException exception) {

            throw new RuntimeException(
                    "Error deleting activity: " + exception.getMessage()
            );
        }
    }

    @Override
    public List<Activity> findAll() {

        final List<Activity> activities =
                new ArrayList<>();

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_ALL)) {

            final ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                activities.add(mapResultSet(resultSet));
            }

            return activities;

        } catch (SQLException exception) {

            throw new RuntimeException(
                    "Error finding activities: " + exception.getMessage()
            );
        }
    }

    private Activity mapResultSet(final ResultSet resultSet)
            throws SQLException {

        return new Activity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("day_of_week"),
                resultSet.getString("schedule"),
                resultSet.getBigDecimal("price"),
                resultSet.getBoolean("is_free"),
                resultSet.getString("employee_id"),
                resultSet.getInt("hotel_id")
        );
    }
}
