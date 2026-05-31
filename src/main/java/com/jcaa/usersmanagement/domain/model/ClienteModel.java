package com.jcaa.usersmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ClienteModel {
    private final Integer idCliente;
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String telefono;
    private final String direccion;
}
