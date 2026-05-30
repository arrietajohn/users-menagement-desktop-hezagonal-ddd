package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public class RoomEntity {
    private Long id;
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private boolean isAvailable;
    private Long hotelId;

    public RoomEntity() {}

    public RoomEntity(Long id, String roomNumber, String type, double pricePerNight, boolean isAvailable, Long hotelId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.hotelId = hotelId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
}