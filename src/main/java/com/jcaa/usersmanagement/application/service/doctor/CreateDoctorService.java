package com.jcaa.usersmanagement.application.service.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.CreateDoctorUseCase;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.application.port.out.doctor.SaveDoctorPort;
import com.jcaa.usersmanagement.application.service.dto.command.doctor.CreateDoctorCommand;
import com.jcaa.usersmanagement.application.service.mapper.doctor.DoctorApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public class CreateDoctorService implements CreateDoctorUseCase {

    private final SaveDoctorPort saveDoctorPort;
    private final GetDoctorPort getDoctorPort;

    public CreateDoctorService(SaveDoctorPort saveDoctorPort, GetDoctorPort getDoctorPort) {
        this.saveDoctorPort = saveDoctorPort;
        this.getDoctorPort = getDoctorPort;
    }

    @Override
    public DoctorModel execute(CreateDoctorCommand command) {
        if (getDoctorPort.findByNif(command.nif()).isPresent()) {
            throw new DoctorValidationException("Ya existe un médico registrado con el NIF proporcionado.");
        }

        DoctorModel doctor = DoctorApplicationMapper.toDomain(command);
        return saveDoctorPort.save(doctor);
    }
}