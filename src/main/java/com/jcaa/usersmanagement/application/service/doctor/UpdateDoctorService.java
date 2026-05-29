package com.jcaa.usersmanagement.application.service.doctor;

import com.jcaa.usersmanagement.application.port.in.doctor.UpdateDoctorUseCase;
import com.jcaa.usersmanagement.application.port.out.doctor.GetDoctorPort;
import com.jcaa.usersmanagement.application.port.out.doctor.SaveDoctorPort;
import com.jcaa.usersmanagement.application.service.dto.command.doctor.UpdateDoctorCommand;
import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import java.time.LocalDateTime;

public class UpdateDoctorService implements UpdateDoctorUseCase {

    private final SaveDoctorPort saveDoctorPort;
    private final GetDoctorPort getDoctorPort;

    public UpdateDoctorService(SaveDoctorPort saveDoctorPort, GetDoctorPort getDoctorPort) {
        this.saveDoctorPort = saveDoctorPort;
        this.getDoctorPort = getDoctorPort;
    }

    @Override
    public DoctorModel execute(String id, UpdateDoctorCommand command) {
        DoctorModel existingDoctor = getDoctorPort.findById(id)
                .orElseThrow(() -> new DoctorValidationException("No se puede actualizar. Médico no encontrado con el ID: " + id));

        // Reconstrucción inmutable con Builder respetando el ID y auditoría
        DoctorModel updatedDoctor = DoctorModel.builder()
                .id(existingDoctor.getId())
                .name(command.name())
                .address(command.address())
                .phone(command.phone())
                .population(command.population())
                .province(command.province())
                .postalCode(command.postalCode())
                .nif(command.nif())
                .socialSecurityNumber(command.socialSecurityNumber())
                .medicalLicense(command.medicalLicense())
                .category(command.category())
                .status(existingDoctor.getStatus())
                .createdAt(existingDoctor.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return saveDoctorPort.save(updatedDoctor);
    }
}