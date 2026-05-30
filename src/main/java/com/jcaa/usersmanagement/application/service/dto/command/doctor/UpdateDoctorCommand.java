package com.jcaa.usersmanagement.application.service.dto.command.doctor;

import com.jcaa.usersmanagement.domain.enums.doctor.DoctorCategory;

public record UpdateDoctorCommand(
        String name,
        String address,
        String phone,
        String population,
        String province,
        String postalCode,
        String nif,
        String socialSecurityNumber,
        String medicalLicense,
        DoctorCategory category
) {}