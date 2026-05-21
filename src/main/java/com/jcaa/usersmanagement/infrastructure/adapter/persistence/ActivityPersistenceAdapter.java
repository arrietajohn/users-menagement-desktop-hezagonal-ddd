package com.jcaa.usersmanagement.infrastructure.adapter.persistence;

import com.jcaa.usersmanagement.application.port.out.ActivityRepository;
import com.jcaa.usersmanagement.domain.model.Activity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ActivityMapper;
import com.jcaa.usersmanagement.infrastructure.persistence.repository.JpaActivityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // con esto se le dice a spring boot que administre este adaptador automáticamente
public class ActivityPersistenceAdapter implements ActivityRepository {

    private final JpaActivityRepository jpaRepository;

    // Inyección por constructor
    public ActivityPersistenceAdapter(JpaActivityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Activity activity) {
        jpaRepository.save(ActivityMapper.toEntity(activity));
    }

    @Override
    public Optional<Activity> findById(String id) {
        return jpaRepository.findById(id)
                .map(ActivityMapper::toDomain);
    }

    @Override
    public void update(Activity activity) {
        jpaRepository.save(ActivityMapper.toEntity(activity));
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Activity> findAll() {
        return jpaRepository.findAll().stream()
                .map(ActivityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
