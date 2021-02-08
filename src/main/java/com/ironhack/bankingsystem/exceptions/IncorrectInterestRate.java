package com.ironhack.bankingsystem.exceptions;

public class IncorrectInterestRate extends RuntimeException{

    public IncorrectInterestRate() {
        super("The interest rate should be less than 0.5");
    }

}
