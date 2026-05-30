package com.jcaa.usersmanagement.domain.model.doctor;

import com.jcaa.usersmanagement.domain.exception.doctor.DoctorValidationException;
import com.jcaa.usersmanagement.domain.enums.doctor.DoctorCategory;

import java.time.LocalDateTime;

public class DoctorModel {
    private final String id;
    private final String name;
    private final String nif;
    private final String medicalLicense; // Número de colegiado
    private final String socialSecurityNumber;
    private final String phone;
    private final String address;
    private final String population;
    private final String province;
    private final String postalCode;
    private final DoctorCategory category;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // Constructor privado para forzar el uso del Builder o validaciones unificadas
    private DoctorModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.nif = builder.nif;
        this.medicalLicense = builder.medicalLicense;
        this.socialSecurityNumber = builder.socialSecurityNumber;
        this.phone = builder.phone;
        this.address = builder.address;
        this.population = builder.population;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.category = builder.category;
        this.status = builder.status != null ? builder.status : "ACTIVE";
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : LocalDateTime.now();

        validate();
    }

    // Reglas de negocio básicas y validaciones requeridas
    private void validate() {
        if (id == null || id.trim().isEmpty()) throw new DoctorValidationException("El ID del médico es requerido.");
        if (name == null || name.trim().isEmpty()) throw new DoctorValidationException("El nombre del médico es requerido.");
        if (nif == null || nif.trim().isEmpty()) throw new DoctorValidationException("El NIF del médico es requerido.");
        if (medicalLicense == null || medicalLicense.trim().isEmpty()) throw new DoctorValidationException("El número de colegiado es requerido.");
        if (category == null) throw new DoctorValidationException("La categoría laboral del médico es requerida.");
    }

    // Getters convencionales (Sin Setters para asegurar Inmutabilidad en DDD)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNif() { return nif; }
    public String getMedicalLicense() { return medicalLicense; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getPopulation() { return population; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }
    public DoctorCategory getCategory() { return category; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // METODO AGREGADO: Punto de entrada estático para instanciar el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Patrón de Diseño: Builder (Especialmente útil en DDD para construir entidades complejas)
    public static class Builder {
        private String id;
        private String name;
        private String nif;
        private String medicalLicense;
        private String socialSecurityNumber;
        private String phone;
        private String address;
        private String population;
        private String province;
        private String postalCode;
        private DoctorCategory category;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder nif(String nif) { this.nif = nif; return this; }
        public Builder medicalLicense(String medicalLicense) { this.medicalLicense = medicalLicense; return this; }
        public Builder socialSecurityNumber(String socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder address(String address) { this.address = address; return this;}
        public Builder population(String population) { this.population = population; return this; }
        public Builder province(String province) { this.province = province; return this; }
        public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        public Builder category(DoctorCategory category) { this.category = category; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public DoctorModel build() {
            return new DoctorModel(this);
        }
    }
}