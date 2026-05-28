package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaGenero;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaName;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProgramaPersistenceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProgramaPersistenceMapper {

  public ProgramaPersistenceDto fromModelToDto(final ProgramaModel model) {
    return new ProgramaPersistenceDto(
        model.getId().value(),
        model.getNombre().value(),
        model.getGenero().value());
  }

  public ProgramaModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return ProgramaModel.create(
        new ProgramaId(resultSet.getLong("id")),
        new ProgramaName(resultSet.getString("nombre")),
        new ProgramaGenero(resultSet.getString("genero")));
  }

  public List<ProgramaModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<ProgramaModel> list = new ArrayList<>();
    while (resultSet.next()) {
      list.add(fromResultSetToModel(resultSet));
    }
    return list;
  }
}
