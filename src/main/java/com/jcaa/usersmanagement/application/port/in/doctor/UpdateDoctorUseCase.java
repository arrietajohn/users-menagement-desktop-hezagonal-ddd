package com.jcaa.usersmanagement.application.port.in.doctor;

import com.jcaa.usersmanagement.application.service.dto.command.doctor.UpdateDoctorCommand;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public interface UpdateDoctorUseCase {
    DoctorModel execute(String id, UpdateDoctorCommand command);
}