package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import java.util.List;

public interface GetAllRangosMilitaresUseCase {
    List<RangoMilitarModel> execute();
}