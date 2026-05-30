package com.jcaa.usersmanagement.application.service.dto.command;

public record CreateRoomCommand(
        String roomNumber,
        String type,
        double pricePerNight,
        Long hotelId
) {}