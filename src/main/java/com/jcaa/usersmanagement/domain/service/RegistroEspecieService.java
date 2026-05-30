package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.NombreCientifico;


public interface RegistroEspecieService {
    Especie registrarEspecie(
            NombreCientifico nombreCientifico,
            String nombreEspañol,
            String descripcion
    );
}
    
