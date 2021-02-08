package com.ironhack.bankingsystem.exceptions;

public class IncorrectCreditLimit extends RuntimeException {

    public IncorrectCreditLimit() {
        super("The Credit Limit cannot be less than 100 nor greater than 100000");
    }
}
