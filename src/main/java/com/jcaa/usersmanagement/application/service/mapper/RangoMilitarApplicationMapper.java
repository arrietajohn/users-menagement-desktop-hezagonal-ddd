package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateRangoMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateRangoMilitarCommand;
import com.jcaa.usersmanagement.domain.enums.LineaMilitar;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.RangoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.domain.valueobject.RangoNombre;
import com.jcaa.usersmanagement.domain.valueobject.TiempoMinimoAscenso;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RangoMilitarApplicationMapper {

    public RangoMilitarModel fromCreateCommandToModel(final CreateRangoMilitarCommand command) {
        return RangoMilitarModel.create(
                new RangoId(command.id()),
                new RangoCodigo(command.codigo()),
                new RangoNombre(command.nombre()),
                new RangoDescripcion(command.descripcion()),
                LineaMilitar.fromString(command.lineaMilitar()),
                new TiempoMinimoAscenso(command.tiempoMinimoAscensoMeses()));
    }

    public RangoMilitarModel fromUpdateCommandToModel(final UpdateRangoMilitarCommand command) {
        return new RangoMilitarModel(
                new RangoId(command.id()),
                new RangoCodigo(command.codigo()),
                new RangoNombre(command.nombre()),
                new RangoDescripcion(command.descripcion()),
                LineaMilitar.fromString(command.lineaMilitar()),
                new TiempoMinimoAscenso(command.tiempoMinimoAscensoMeses()));
    }
}
