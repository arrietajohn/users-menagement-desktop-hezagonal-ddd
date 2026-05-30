package com.jcaa.usersmanagement.application.service.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.GetDoctorByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;

public class GetDoctorByIdService implements GetDoctorByIdUseCase {

    private final GetDoctorPort getDoctorPort;

    public GetDoctorByIdService(GetDoctorPort getDoctorPort) {
        this.getDoctorPort = getDoctorPort;
    }

    @Override
    public DoctorModel execute(String id) {
        return getDoctorPort.findById(id)
                .orElseThrow(() -> new DoctorValidationException("Médico no encontrado con el ID: " + id));
    }
}