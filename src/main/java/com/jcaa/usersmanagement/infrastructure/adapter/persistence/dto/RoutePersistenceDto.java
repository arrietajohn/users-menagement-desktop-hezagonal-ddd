package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record RoutePersistenceDto(
        String id,
        String name,
        String neighborhood,
        String school,
        String journey,
        Integer maxCapacity,
        Integer availableSeats,
        Boolean active,
        String createdAt,
        String updatedAt) {}