package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.doctor.DoctorCategory;
import com.jcaa.usersmanagement.domain.model.doctor.DoctorModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.DoctorPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.doctor.DoctorEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DoctorPersistenceMapper {

    public DoctorPersistenceDto fromModelToDto(final DoctorModel doctor) {
        return new DoctorPersistenceDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getAddress(),
                doctor.getPhone(),
                doctor.getPopulation(),
                doctor.getProvince(),
                doctor.getPostalCode(),
                doctor.getNif(),
                doctor.getSocialSecurityNumber(),
                doctor.getMedicalLicense(),
                doctor.getCategory().name(),
                doctor.getStatus()
        );
    }

    public DoctorEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        DoctorEntity entity = new DoctorEntity();
        entity.setId(resultSet.getString("id"));
        entity.setName(resultSet.getString("name"));
        entity.setAddress(resultSet.getString("address"));
        entity.setPhone(resultSet.getString("phone"));
        entity.setPopulation(resultSet.getString("population"));
        entity.setProvince(resultSet.getString("province"));
        entity.setPostalCode(resultSet.getString("postal_code"));
        entity.setNif(resultSet.getString("nif"));
        entity.setSocialSecurityNumber(resultSet.getString("social_security_number"));
        entity.setMedicalLicense(resultSet.getString("medical_license"));
        entity.setCategory(resultSet.getString("category"));
        entity.setStatus(resultSet.getString("status"));
        return entity;
    }

    public DoctorModel fromEntityToModel(final DoctorEntity entity) {
        return DoctorModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .population(entity.getPopulation())
                .province(entity.getProvince())
                .postalCode(entity.getPostalCode())
                .nif(entity.getNif())
                .socialSecurityNumber(entity.getSocialSecurityNumber())
                .medicalLicense(entity.getMedicalLicense())
                .category(DoctorCategory.valueOf(entity.getCategory()))
                .status(entity.getStatus())
                .build();
    }

    public DoctorModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<DoctorModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<DoctorModel> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(fromResultSetToModel(resultSet));
        }
        return doctors;
    }
}