package com.jcaa.usersmanagement.infrastructure.adapter.persistence;

import com.jcaa.usersmanagement.application.port.out.ActivityRepository;
import com.jcaa.usersmanagement.domain.model.Activity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ActivityPersistenceAdapter implements ActivityRepository {

    private final Connection connection;

    public ActivityPersistenceAdapter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Activity activity) {
        // TODO: Implementar el INSERT INTO usando PreparedStatement con la inyección de connection
    }

    @Override
    public Optional<Activity> findById(String id) {
        // TODO: Implementar el SELECT * FROM usando PreparedStatement
        return Optional.empty();
    }

    @Override
    public void update(Activity activity) {
        // TODO: Implementar el UPDATE usando PreparedStatement
    }

    @Override
    public void deleteById(String id) {
        // TODO: Implementar el DELETE FROM usando PreparedStatement
    }

    @Override
    public List<Activity> findAll() {
        // TODO: Implementar el SELECT * para listar todo
        return List.of();
    }
}