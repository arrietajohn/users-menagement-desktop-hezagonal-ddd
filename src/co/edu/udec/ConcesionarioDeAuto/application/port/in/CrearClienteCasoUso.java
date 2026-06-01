package edu.udec.ConcesionarioDeAuto.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.domain.model.UserModel;
import edu.udec.ConcesionarioDeAuto.application.service.dto.command.ComandoCrearCliente;
import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CrearClienteCasoUso {
  Cliente execute(@NotNull @Valid ComandoCrearCliente comandoCrear);
}
