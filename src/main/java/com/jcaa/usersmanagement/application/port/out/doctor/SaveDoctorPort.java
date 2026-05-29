package com.jcaa.usersmanagement.application.port.out.doctor;

import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public interface SaveDoctorPort {
    DoctorModel save(DoctorModel doctor);
}