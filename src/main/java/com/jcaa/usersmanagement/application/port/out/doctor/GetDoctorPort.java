package com.jcaa.usersmanagement.application.port.out.doctor;

import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import java.util.List;
import java.util.Optional;

public interface GetDoctorPort {
    Optional<DoctorModel> findById(String id);
    Optional<DoctorModel> findByNif(String nif);
    List<DoctorModel> findAll();
}