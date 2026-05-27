package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.RangoMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListRangosMilitaresHandler implements OperationHandler {

    private final RangoMilitarController controller;
    private final RangoMilitarResponsePrinter printer;

    @Override
    public void handle() {
        printer.printList(controller.listAllRangos());
    }
}
