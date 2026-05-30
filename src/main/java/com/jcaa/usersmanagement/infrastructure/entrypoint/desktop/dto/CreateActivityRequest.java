package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record CreateActivityRequest(
        String id,
        String name,
        String description,
        String dayOfWeek,
        String schedule,
        BigDecimal price,
        boolean isFree,
        String employeeId,
        int hotelId) {}
