package com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor;

public record CreateDoctorRequest(
        String name,
        String address,
        String phone,
        String population,
        String province,
        String postalCode,
        String nif,
        String socialSecurityNumber,
        String medicalLicense,
        String category
) {}