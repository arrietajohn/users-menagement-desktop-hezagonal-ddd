package com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.doctor;

import com.jcaa.usersmanagement.infrastructure.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.desktop.cli.io.DoctorResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;
import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListDoctorsHandler implements OperationHandler {

    private final DoctorController doctorController;
    private final DoctorResponsePrinter printer;

    @Override
    public void handle() {
        final List<DoctorResponse> doctors = doctorController.getAll();
        printer.printList(doctors);
    }
}