package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import java.util.List;

public interface GetAllRangosMilitaresPort {
    List<RangoMilitarModel> getAll();
}