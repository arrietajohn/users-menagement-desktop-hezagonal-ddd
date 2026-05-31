package com.jcaa.usersmanagement.application.service.mapper;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSucursalCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteSucursalCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateSucursalCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetSucursalByIdQuery;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.BancoId;
import com.jcaa.usersmanagement.domain.valueobject.SucursalCityOperation;
import com.jcaa.usersmanagement.domain.valueobject.SucursalPostalCode;
import com.jcaa.usersmanagement.domain.valueobject.SucursalDirection;
import com.jcaa.usersmanagement.domain.valueobject.SucursalNumber;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import lombok.experimental.UtilityClass;

@UtilityClass //Hace el constructor privado - hace todos los métodos static y hace la clase final
public class SucursalApplicationMapper {

    public SucursalModel fromCreateCommandToModel(final CreateSucursalCommand command) {
        return SucursalModel.create(
                new SucursaId(command.id()),
                new SucursalNumber(command.numero()),
                new SucursalDirection(command.direccion()),
                new SucursalPostalCode(command.codigoPostal()),
                new SucursalCityOperation(command.ciudad()),
                new BancoId(command.bancoId())
        );
    }

    public SucursalModel fromUpdateCommandToModel(final UpdateSucursalCommand command) {
        return new SucursalModel(
                new SucursaId(command.id()),
                new SucursalNumber(command.numero()),
                new SucursalDirection(command.direccion()),
                new SucursalPostalCode(command.codigoPostal()),
                new SucursalCityOperation(command.ciudad()),
                new BancoId(command.bancoId())
        );
    }

    public SucursaId fromGetSucursalByIdQueryToSucursalId(final GetSucursalByIdQuery query) {
        return new SucursaId(query.id());
    }

    public SucursaId fromDeleteCommandToSucursalId(final DeleteSucursalCommand command) {
        return new SucursaId(command.id());
    }
}

