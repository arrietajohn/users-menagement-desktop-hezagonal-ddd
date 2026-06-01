package edu.udec.ConcesionarioDeAuto.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.domain.model.UserModel;
import edu.udec.ConcesionarioDeAuto.application.service.dto.command.ComandoActualizarCliente;
import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActualizarClienteCasoUso {
  Cliente execute(@NotNull @Valid ComandoActualizarCliente commandoActualizar);
}
