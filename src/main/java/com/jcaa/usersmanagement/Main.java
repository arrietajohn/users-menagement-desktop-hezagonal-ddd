package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;


import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.MySqlSubastaAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.MySqlOfertaAdapter;
import com.jcaa.usersmanagement.application.service.SubastaService;
import com.jcaa.usersmanagement.application.service.OfertaService;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SubastaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.OfertaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.PlataformaSubastasView;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        log.info("Starting Users Management System...");
        final DependencyContainer container = new DependencyContainer();


        MySqlSubastaAdapter subastaAdapter = new MySqlSubastaAdapter();
        MySqlOfertaAdapter ofertaAdapter = new MySqlOfertaAdapter();

        SubastaService subastaService = new SubastaService(subastaAdapter);
        OfertaService ofertaService = new OfertaService(ofertaAdapter, subastaAdapter);

        SubastaController subastaController = new SubastaController(subastaService);
        OfertaController ofertaController = new OfertaController(ofertaService);

        PlataformaSubastasView vista = new PlataformaSubastasView(subastaController, ofertaController);
        vista.setVisible(true);

        try (final Scanner scanner = new Scanner(System.in)) {
            new UserManagementCli(container.userController(), new ConsoleIO(scanner, System.out)).start();
        }
    }
}