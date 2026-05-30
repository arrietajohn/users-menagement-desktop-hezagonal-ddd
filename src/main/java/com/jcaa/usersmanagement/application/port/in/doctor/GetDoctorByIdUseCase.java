package com.jcaa.usersmanagement.application.port.in.doctor;

import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public interface GetDoctorByIdUseCase {
    DoctorModel execute(String id);
}