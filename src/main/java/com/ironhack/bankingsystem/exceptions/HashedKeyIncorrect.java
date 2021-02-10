package com.ironhack.bankingsystem.exceptions;

public class HashedKeyIncorrect extends RuntimeException{

    public HashedKeyIncorrect() {
        super("Hashed Key do not match");
    }
}
