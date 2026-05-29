package com.jcaa.usersmanagement.application.service.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.DeleteDoctorUseCase;
import com.jcaa.usersmanagement.application.port.out.doctor.DeleteDoctorPort;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;

public class DeleteDoctorService implements DeleteDoctorUseCase {

    private final DeleteDoctorPort deleteDoctorPort;
    private final GetDoctorPort getDoctorPort;

    public DeleteDoctorService(DeleteDoctorPort deleteDoctorPort, GetDoctorPort getDoctorPort) {
        this.deleteDoctorPort = deleteDoctorPort;
        this.getDoctorPort = getDoctorPort;
    }

    @Override
    public void execute(String id) {
        getDoctorPort.findById(id)
                .orElseThrow(() -> new DoctorValidationException("No se puede eliminar. Médico no encontrado con el ID: " + id));

        deleteDoctorPort.deleteById(id);
    }
}