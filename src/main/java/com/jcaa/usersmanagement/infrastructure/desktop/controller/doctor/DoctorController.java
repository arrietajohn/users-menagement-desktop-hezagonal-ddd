package com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.CreateDoctorUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.DeleteDoctorUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.GetAllDoctorsUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.GetDoctorByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.UpdateDoctorUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.doctor.UpdateDoctorCommand;
import com.jcaa.usersmanagement.domain.enums.doctor.DoctorCategory;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.CreateDoctorRequest;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import com.jcaa.usersmanagement.infrastructure.desktop.mapper.doctor.DoctorDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class DoctorController {

    private final CreateDoctorUseCase createDoctorUseCase;
    private final GetDoctorByIdUseCase getDoctorByIdUseCase;
    private final GetAllDoctorsUseCase getAllDoctorsUseCase;
    private final UpdateDoctorUseCase updateDoctorUseCase;
    private final DeleteDoctorUseCase deleteDoctorUseCase;

    public DoctorResponse create(final CreateDoctorRequest request) {
        var command = DoctorDesktopMapper.toCreateCommand(request);
        DoctorModel savedDoctor = createDoctorUseCase.execute(command);
        return DoctorDesktopMapper.toResponse(savedDoctor);
    }

    public DoctorResponse getById(final String id) {
        DoctorModel doctor = getDoctorByIdUseCase.execute(id);
        return DoctorDesktopMapper.toResponse(doctor);
    }

    public List<DoctorResponse> getAll() {
        List<DoctorModel> doctors = getAllDoctorsUseCase.execute();
        return DoctorDesktopMapper.toResponseList(doctors);
    }

    public DoctorResponse update(final String id, final CreateDoctorRequest request) {
        var command = new UpdateDoctorCommand(
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
        DoctorModel updatedDoctor = updateDoctorUseCase.execute(id, command);
        return DoctorDesktopMapper.toResponse(updatedDoctor);
    }

    public void delete(final String id) {
        deleteDoctorUseCase.execute(id);
    }
}