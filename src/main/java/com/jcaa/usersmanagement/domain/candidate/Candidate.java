package com.jcaa.usersmanagement.domain.candidate;


public class Candidate {

    private String id;
    private String dni;
    private String name;
    private String party;

    public Candidate(String id, String dni, String name, String party) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.party = party;
    }

    public String getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }
}
