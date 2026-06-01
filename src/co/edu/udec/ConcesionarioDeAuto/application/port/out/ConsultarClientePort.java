package edu.udec.ConcesionarioDeAuto.application.port.out;

import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.ClienteId;

import java.util.Optional;

public interface ConsultarClientePort {
  Optional<Cliente> consultarCliente(ClienteId clienteId);
}
