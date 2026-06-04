package com.jcaa.usersmanagement.domain.voter;

public class Voter {

    private final int id;
    private final String dni;
    private final String fullName;
    private final String email;
    private final String commune;

    public Voter(int id, String dni, String fullName, String email, String commune) {
        this.id = id;
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.commune = commune;
    }

    public int getId() { return id; }
    public String getDni() { return dni; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getCommune() { return commune; }
}