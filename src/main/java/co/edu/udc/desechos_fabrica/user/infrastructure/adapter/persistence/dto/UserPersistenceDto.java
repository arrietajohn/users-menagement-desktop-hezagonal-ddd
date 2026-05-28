package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.dto;

public record UserPersistenceDto(
    String id,
    String name,
    String email,
    String password,
    String role,
    String status,
    String createdAt,
    String updatedAt) {}
