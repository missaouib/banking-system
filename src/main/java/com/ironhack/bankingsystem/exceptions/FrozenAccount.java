package com.ironhack.bankingsystem.exceptions;

public class FrozenAccount extends RuntimeException{

    public FrozenAccount() {
        super("Account is frozen does not allow operations ");
    }
}
