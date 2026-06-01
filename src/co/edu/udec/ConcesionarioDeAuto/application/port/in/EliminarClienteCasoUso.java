package edu.udec.ConcesionarioDeAuto.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteUserCommand;
import edu.udec.ConcesionarioDeAuto.application.service.dto.command.ComandoBorrarCliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface EliminarClienteCasoUso {
  void execute(@NotNull @Valid ComandoBorrarCliente commandoEliminar);
}
