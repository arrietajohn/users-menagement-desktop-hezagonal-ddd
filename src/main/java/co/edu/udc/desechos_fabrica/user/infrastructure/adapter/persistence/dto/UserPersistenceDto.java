package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.dto;

public record UserPersistenceDto(
    String firstName,
    String lastName,
    String email,
    String password,
    String nit,
    String role,
    String status,
    String createdAt,
    String updatedAt) {}
