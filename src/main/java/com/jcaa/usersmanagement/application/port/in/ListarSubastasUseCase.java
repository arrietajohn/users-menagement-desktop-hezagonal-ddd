package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Subasta;
import java.util.List;

public interface ListarSubastasUseCase {
    List<Subasta> listarTodas();
}