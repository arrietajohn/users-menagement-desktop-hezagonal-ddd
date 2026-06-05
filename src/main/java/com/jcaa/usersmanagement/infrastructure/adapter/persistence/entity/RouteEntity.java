package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record RouteEntity(
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