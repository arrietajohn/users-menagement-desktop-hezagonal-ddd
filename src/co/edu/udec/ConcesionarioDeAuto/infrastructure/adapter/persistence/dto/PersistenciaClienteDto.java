package edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.dto;

public record PersistenciaClienteDto(
        String id_cliente,
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        String direccion,
        String telefono,
        String createdAt,
        String updatedAt) {}
