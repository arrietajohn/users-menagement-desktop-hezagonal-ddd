package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.AsignaturaAreaConocimiento;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaCarrera;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaContenidoTematico;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaDescripcion;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaId;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaNombre;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaNombreCompleto;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaNumeroCreditos;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaProfesor;
import com.jcaa.usersmanagement.domain.valueobject.AsignaturaSemestre;
import java.util.Map;
import lombok.Value;

@Value
public class AsignaturaModel {

  AsignaturaId id;
  AsignaturaNombre nombre;
  AsignaturaNombreCompleto nombreCompleto;
  AsignaturaDescripcion descripcion;
  AsignaturaAreaConocimiento areaConocimiento;
  AsignaturaCarrera carrera;
  AsignaturaNumeroCreditos numeroCreditos;
  AsignaturaContenidoTematico contenidoTematico;
  AsignaturaSemestre semestre;
  AsignaturaProfesor profesor;

  public static AsignaturaModel create(
      final AsignaturaId id,
      final AsignaturaNombre nombre,
      final AsignaturaNombreCompleto nombreCompleto,
      final AsignaturaDescripcion descripcion,
      final AsignaturaAreaConocimiento areaConocimiento,
      final AsignaturaCarrera carrera,
      final AsignaturaNumeroCreditos numeroCreditos,
      final AsignaturaContenidoTematico contenidoTematico,
      final AsignaturaSemestre semestre,
      final AsignaturaProfesor profesor) {
    return new AsignaturaModel(
        id,
        nombre,
        nombreCompleto,
        descripcion,
        areaConocimiento,
        carrera,
        numeroCreditos,
        contenidoTematico,
        semestre,
        profesor);
  }

  // change methods that return a new instance with the updated value
  public AsignaturaModel changeNombre(final AsignaturaNombre nombre) {
    return new AsignaturaModel(
        this.id,
        nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeNombreCompleto(final AsignaturaNombreCompleto nombreCompleto) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeDescripcion(final AsignaturaDescripcion descripcion) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeAreaConocimiento(final AsignaturaAreaConocimiento areaConocimiento) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeCarrera(final AsignaturaCarrera carrera) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeNumeroCreditos(final AsignaturaNumeroCreditos numeroCreditos) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeContenidoTematico(final AsignaturaContenidoTematico contenidoTematico) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        contenidoTematico,
        this.semestre,
        this.profesor);
  }

  public AsignaturaModel changeSemestre(final AsignaturaSemestre semestre) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        semestre,
        this.profesor);
  }

  public AsignaturaModel changeProfesor(final AsignaturaProfesor profesor) {
    return new AsignaturaModel(
        this.id,
        this.nombre,
        this.nombreCompleto,
        this.descripcion,
        this.areaConocimiento,
        this.carrera,
        this.numeroCreditos,
        this.contenidoTematico,
        this.semestre,
        profesor);
  }

  public Map<String, Object> toMap() {
    return Map.of(
        "id", this.id.value(),
        "nombre", this.nombre.value(),
        "nombreCompleto", this.nombreCompleto.value(),
        "descripcion", this.descripcion.value(),
        "areaConocimiento", this.areaConocimiento.value(),
        "carrera", this.carrera.value(),
        "numeroCreditos", this.numeroCreditos.value(),
        "contenidoTematico", this.contenidoTematico.value(),
        "semestre", this.semestre.value(),
        "profesor", this.profesor.value());
  }
}
