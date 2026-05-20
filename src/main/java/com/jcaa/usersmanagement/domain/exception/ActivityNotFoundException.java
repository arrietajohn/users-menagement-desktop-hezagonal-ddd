package com.jcaa.usersmanagement.domain.exception;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(String id) {
        super("La actividad con ID '" + id + "' no fue encontrada en el sistema");
    }
}
