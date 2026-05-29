package com.jcaa.usersmanagement.application.service.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.GetAllDoctorsUseCase;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import java.util.List;

public class GetAllDoctorsService implements GetAllDoctorsUseCase {

    private final GetDoctorPort getDoctorPort;

    public GetAllDoctorsService(GetDoctorPort getDoctorPort) {
        this.getDoctorPort = getDoctorPort;
    }

    @Override
    public List<DoctorModel> execute() {
        return getDoctorPort.findAll();
    }
}