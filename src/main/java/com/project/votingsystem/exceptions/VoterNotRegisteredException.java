package com.project.votingsystem.exceptions;

public class VoterNotRegisteredException extends RuntimeException {
    public VoterNotRegisteredException(String message) {
        super(message);
    }
}
