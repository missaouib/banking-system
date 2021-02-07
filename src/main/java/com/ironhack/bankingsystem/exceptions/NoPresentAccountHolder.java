package com.ironhack.bankingsystem.exceptions;


public class NoPresentAccountHolder extends RuntimeException{
    public NoPresentAccountHolder() {
        super("The Account Holder is not present");
    }
}
