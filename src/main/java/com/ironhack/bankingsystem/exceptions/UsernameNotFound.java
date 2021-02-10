package com.ironhack.bankingsystem.exceptions;

public class UsernameNotFound extends RuntimeException{

    public UsernameNotFound() {
        super("User does not exist");
    }
}
