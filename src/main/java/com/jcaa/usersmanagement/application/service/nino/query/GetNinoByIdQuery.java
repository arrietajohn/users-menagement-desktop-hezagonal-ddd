package com.jcaa.usersmanagement.application.service.nino.query;

public class GetNinoByIdQuery {

    private final Long id;

    public GetNinoByIdQuery(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}