package edu.udec.ConcesionarioDeAuto.application.port.in;


import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;

import java.util.List;

public interface ListarTodoClienteCasoUso {
  List<Cliente> execute();
}
