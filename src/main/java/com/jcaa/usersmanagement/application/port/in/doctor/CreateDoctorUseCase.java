package com.jcaa.usersmanagement.application.port.in.doctor;

import com.jcaa.usersmanagement.application.service.dto.command.doctor.CreateDoctorCommand;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public interface CreateDoctorUseCase {
    DoctorModel execute(CreateDoctorCommand command);
}