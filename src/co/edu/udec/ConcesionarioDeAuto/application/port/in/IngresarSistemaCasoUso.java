package edu.udec.ConcesionarioDeAuto.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.LoginCommand;
import com.jcaa.usersmanagement.domain.model.UserModel;
import edu.udec.ConcesionarioDeAuto.application.service.dto.command.ComandoIngresarCliente;
import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface IngresarSistemaCasoUso {
  Cliente execute(@NotNull @Valid ComandoIngresarCliente commandoIngresar);
}
