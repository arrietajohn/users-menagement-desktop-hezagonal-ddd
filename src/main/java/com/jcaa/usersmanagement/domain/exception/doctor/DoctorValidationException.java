package com.jcaa.usersmanagement.domain.exception.doctor;

public class DoctorValidationException extends RuntimeException {
    public DoctorValidationException(String message) {
        super(message);
    }
}