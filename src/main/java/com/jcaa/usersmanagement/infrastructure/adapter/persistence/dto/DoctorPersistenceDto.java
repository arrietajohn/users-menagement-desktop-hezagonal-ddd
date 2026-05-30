package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record DoctorPersistenceDto(
        String id,
        String name,
        String address,
        String phone,
        String population,
        String province,
        String postalCode,
        String nif,
        String socialSecurityNumber,
        String medicalLicense,
        String category,
        String status
) {}