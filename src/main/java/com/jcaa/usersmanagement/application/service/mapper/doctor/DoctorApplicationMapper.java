package com.jcaa.usersmanagement.application.service.mapper.doctor;

import com.jcaa.usersmanagement.application.service.dto.command.doctor.CreateDoctorCommand;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import java.util.UUID;

public class DoctorApplicationMapper {

    public static DoctorModel toDomain(CreateDoctorCommand command) {
        if (command == null) {
            return null;
        }

        return DoctorModel.builder()
                .id(UUID.randomUUID().toString())
                .name(command.name())
                .address(command.address())
                .phone(command.phone())
                .population(command.population())
                .province(command.province())
                .postalCode(command.postalCode())
                .nif(command.nif())
                .socialSecurityNumber(command.socialSecurityNumber())
                .medicalLicense(command.medicalLicense())
                .category(command.category())
                .build();
    }
}