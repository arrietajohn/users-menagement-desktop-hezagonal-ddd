package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.LineaMilitar;
import com.jcaa.usersmanagement.domain.valueobject.RangoCodigo;
import com.jcaa.usersmanagement.domain.valueobject.RangoDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.domain.valueobject.RangoNombre;
import com.jcaa.usersmanagement.domain.valueobject.TiempoMinimoAscenso;
import lombok.Value;

@Value
public class RangoMilitarModel {

    RangoId id;
    RangoCodigo codigo;
    RangoNombre nombre;
    RangoDescripcion descripcion;
    LineaMilitar lineaMilitar;
    TiempoMinimoAscenso tiempoMinimoAscenso;

    public static RangoMilitarModel create(
            final RangoId id,
            final RangoCodigo codigo,
            final RangoNombre nombre,
            final RangoDescripcion descripcion,
            final LineaMilitar lineaMilitar,
            final TiempoMinimoAscenso tiempoMinimoAscenso) {
        return new RangoMilitarModel(id, codigo, nombre, descripcion, lineaMilitar, tiempoMinimoAscenso);
    }
}