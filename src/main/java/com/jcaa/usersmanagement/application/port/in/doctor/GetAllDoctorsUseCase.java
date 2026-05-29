package com.jcaa.usersmanagement.application.port.in.doctor;

import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import java.util.List;

public interface GetAllDoctorsUseCase {
    List<DoctorModel> execute();
}