package edu.udec.ConcesionarioDeAuto.application.port.out;

import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;

public interface GuardarClientePort {
  Cliente guardar(Cliente cliente);
}
