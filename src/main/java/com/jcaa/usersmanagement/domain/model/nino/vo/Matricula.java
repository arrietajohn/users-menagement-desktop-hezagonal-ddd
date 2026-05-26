package com.jcaa.usersmanagement.domain.model.nino.vo;

public class Matricula {

    private final String value;

    public Matricula(String value) {
        this.value = validate(value);
    }

    private String validate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía");
        }
        String trimmed = value.trim().toUpperCase();
        if (trimmed.length() < 5 || trimmed.length() > 20) {
            throw new IllegalArgumentException("La matrícula debe tener entre 5 y 20 caracteres");
        }
        return trimmed;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
