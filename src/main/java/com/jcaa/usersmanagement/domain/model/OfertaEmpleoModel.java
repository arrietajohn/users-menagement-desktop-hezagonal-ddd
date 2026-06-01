package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.ofertaempleo.*;
import lombok.Value;

@Value
public class OfertaEmpleoModel {

    OfertaEmpleoId id;
    Titulo titulo;
    Descripcion descripcion;
    Empresa empresa;
    Ubicacion ubicacion;
    Salario salario;
    EstadoOferta estado;
}