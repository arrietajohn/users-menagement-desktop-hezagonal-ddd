package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.entity;

public record UserEntity(
    String firstName,
    String lastName,
    String email,
    String password,
    String role,
    String status,
    String createdAt,
    String updatedAt) {}
