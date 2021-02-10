package com.ironhack.bankingsystem.exceptions;

public class NoPresentThirdParty extends RuntimeException{

    public NoPresentThirdParty() {
        super("The Third Party is not present");
    }
}
