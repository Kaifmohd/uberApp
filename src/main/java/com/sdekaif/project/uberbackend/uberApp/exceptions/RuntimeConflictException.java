package com.sdekaif.project.uberbackend.uberApp.exceptions;

public class RuntimeConflictException extends RuntimeException {
    public RuntimeConflictException() {
    }

    public RuntimeConflictException(String message) {
        super(message);
    }
}
