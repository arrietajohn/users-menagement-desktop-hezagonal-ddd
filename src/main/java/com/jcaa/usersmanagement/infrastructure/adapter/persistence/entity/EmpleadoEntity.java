package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record EmpleadoEntity(
    Long id,
    String nombre,
    String apellido,
    String email,
    String cargo,
    String fechaContratacion,
    String estado,
    String createdAt,
    String updatedAt) {}
