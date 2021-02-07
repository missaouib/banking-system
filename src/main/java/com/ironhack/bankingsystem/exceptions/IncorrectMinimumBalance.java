package com.ironhack.bankingsystem.exceptions;

public class IncorrectMinimumBalance extends RuntimeException{

    public IncorrectMinimumBalance() {
        super("Minimum balance should be greater than 100");
    }
}
