package com.jcaa.usersmanagement.domain.voter;

public class VoterNotFoundException extends RuntimeException {
    public VoterNotFoundException(String dni) {
        super("Voter not found with DNI: " + dni);
    }
}
