package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "activities")
public class ActivityEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "day_of_week", nullable = false, length = 20)
    private String dayOfWeek;

    @Column(nullable = false, length = 50)
    private String schedule;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_free", nullable = false)
    private boolean isFree;

    @Column(name = "employee_id", nullable = false, length = 36)
    private String employeeId;

    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    // Constructor vacío obligatorio para JPA
    public ActivityEntity() {}

    // Constructor completo
    public ActivityEntity(String id, String name, String description, String dayOfWeek,
                          String schedule, BigDecimal price, boolean isFree,
                          String employeeId, int hotelId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.schedule = schedule;
        this.price = price;
        this.isFree = isFree;
        this.employeeId = employeeId;
        this.hotelId = hotelId;
    }

    // Getters y Setters (Fundamentales para el Mapper)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isFree() { return isFree; }
    public void setFree(boolean free) { isFree = free; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
}
