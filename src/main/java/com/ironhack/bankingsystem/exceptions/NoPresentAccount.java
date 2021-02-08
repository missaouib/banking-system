package com.ironhack.bankingsystem.exceptions;

public class NoPresentAccount extends RuntimeException{

    public NoPresentAccount() {
        super("Account is not present");
    }
}
