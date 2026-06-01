package edu.udec.ConcesionarioDeAuto.application.port.out;


import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;

import java.util.List;

public interface ListarTodoClientePort{
  List<Cliente> ListarCliente();
}
