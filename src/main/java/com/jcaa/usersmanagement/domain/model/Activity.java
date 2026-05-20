package com.jcaa.usersmanagement.domain.model;

import java.math.BigDecimal;

public class Activity {
    //atributos relacionados con la entidad Activity, que representa cada uno de los servicios de recreación de los hoteles
    private final String id;
    private String name;
    private String description;
    private String dayOfWeek;
    private String schedule;
    private BigDecimal price;
    private boolean isFree;
    private final String employeeId;
    private final int hotelId;

    // con este constructor inicializamos la actividad
    public Activity(String id, String name, String description, String dayOfWeek,
                    String schedule, BigDecimal price, boolean isFree,
                    String employeeId, int hotelId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.schedule = schedule;
        this.isFree = isFree;
        // dato importante: regla de negocio, si es gratis, el precio siempre se fuerza a cero
        this.price = isFree ? BigDecimal.ZERO : price;
        this.employeeId = employeeId;
        this.hotelId = hotelId;
    }

    // Comportamiento de Dominio para actualizar datos de forma controlada
    public void updateDetails(String name, String description, String dayOfWeek,
                              String schedule, BigDecimal price, boolean isFree) {
        this.name = name;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.schedule = schedule;
        this.isFree = isFree;
        this.price = isFree ? BigDecimal.ZERO : price;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDayOfWeek() { return dayOfWeek; }
    public String getSchedule() { return schedule; }
    public BigDecimal getPrice() { return price; }
    public boolean isFree() { return isFree; }
    public String getEmployeeId() { return employeeId; }
    public int getHotelId() { return hotelId; }
}
