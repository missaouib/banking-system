package com.ironhack.bankingsystem.exceptions;

public class UnauthorizedAccess extends RuntimeException{

    public UnauthorizedAccess() {
        super("access denied");
    }
}
