package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllRangosMilitaresUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllRangosMilitaresPort;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class GetAllRangosMilitaresService implements GetAllRangosMilitaresUseCase {

    private final GetAllRangosMilitaresPort getAllRangosMilitaresPort;

    @Override
    public List<RangoMilitarModel> execute() {
        return getAllRangosMilitaresPort.getAll();
    }
}
