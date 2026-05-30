package com.jcaa.usersmanagement.infrastructure.desktop.mapper.doctor;

import com.jcaa.usersmanagement.application.service.dto.command.doctor.CreateDoctorCommand;
import com.jcaa.usersmanagement.domain.enums.doctor.DoctorCategory;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.CreateDoctorRequest;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DoctorDesktopMapper {

    public CreateDoctorCommand toCreateCommand(final CreateDoctorRequest request) {
        return new CreateDoctorCommand(
                request.name(),
                request.address(),
                request.phone(),
                request.population(),
                request.province(),
                request.postalCode(),
                request.nif(),
                request.socialSecurityNumber(),
                request.medicalLicense(),
                DoctorCategory.valueOf(request.category().toUpperCase())
        );
    }

    public DoctorResponse toResponse(final DoctorModel model) {
        return new DoctorResponse(
                model.getId(),
                model.getName(),
                model.getAddress(),
                model.getPhone(),
                model.getPopulation(),
                model.getProvince(),
                model.getPostalCode(),
                model.getNif(),
                model.getSocialSecurityNumber(),
                model.getMedicalLicense(),
                model.getCategory().name(),
                model.getStatus()
        );
    }

    public List<DoctorResponse> toResponseList(final List<DoctorModel> models) {
        final List<DoctorResponse> responses = new ArrayList<>();
        for (final DoctorModel model : models) {
            responses.add(toResponse(model));
        }
        return responses;
    }
}