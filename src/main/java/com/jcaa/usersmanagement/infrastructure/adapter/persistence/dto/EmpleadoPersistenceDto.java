package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record EmpleadoPersistenceDto(
    Long id,
    String nombre,
    String apellido,
    String email,
    String cargo,
    String fechaContratacion,
    String estado) {}
