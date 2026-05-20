package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateActivityUseCase;
import com.jcaa.usersmanagement.application.port.in.GetActivityUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateDeleteActivityUseCase;
import com.jcaa.usersmanagement.application.port.out.ActivityRepository;
import com.jcaa.usersmanagement.domain.exception.ActivityNotFoundException;
import com.jcaa.usersmanagement.domain.model.Activity;

import java.util.List;
import java.util.Optional;

public class ActivityService implements CreateActivityUseCase, GetActivityUseCase, UpdateDeleteActivityUseCase {

    private final ActivityRepository activityRepository;

    // Inyección por constructor (Cumple el principio de Inversión de Dependencias - DIP)
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void execute(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public Optional<Activity> executeFindById(String id) {
        return activityRepository.findById(id);
    }

    @Override
    public List<Activity> executeFindAll() {
        return activityRepository.findAll();
    }

    @Override
    public void executeUpdate(Activity activity) {
        // Validación obligatoria: Verificar que exista antes de intentar actualizar
        activityRepository.findById(activity.getId())
                .orElseThrow(() -> new ActivityNotFoundException(activity.getId()));

        activityRepository.update(activity);
    }

    @Override
    public void executeDelete(String id) {
        // Validación obligatoria: Verificar que exista antes de intentar eliminar
        activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException(id));

        activityRepository.deleteById(id);
    }
}
