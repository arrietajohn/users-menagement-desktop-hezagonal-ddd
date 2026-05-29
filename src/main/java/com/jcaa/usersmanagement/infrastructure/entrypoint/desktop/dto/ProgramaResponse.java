package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgramaResponse {
  private Long id;
  private String nombre;
  private String genero;
}
