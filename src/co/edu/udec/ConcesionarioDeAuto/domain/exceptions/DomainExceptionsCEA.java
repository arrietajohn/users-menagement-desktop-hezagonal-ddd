package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public abstract class DomainExceptionsCEA extends RuntimeException {
    public DomainExceptionsCEA(String message) {
        super(message);
    }
}
