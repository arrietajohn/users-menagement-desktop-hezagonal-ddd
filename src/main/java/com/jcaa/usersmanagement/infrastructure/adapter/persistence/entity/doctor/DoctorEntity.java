package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.doctor;

import java.time.LocalDateTime;

public class DoctorEntity {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String population;
    private String province;
    private String postalCode;
    private String nif;
    private String socialSecurityNumber;
    private String medicalLicense;
    private String category;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DoctorEntity() {}

    // Getters y Setters tradicionales
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPopulation() { return population; }
    public void setPopulation(String population) { this.population = population; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void setSocialSecurityNumber(String socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }
    public String getMedicalLicense() { return medicalLicense; }
    public void setMedicalLicense(String medicalLicense) { this.medicalLicense = medicalLicense; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}