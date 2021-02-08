package com.ironhack.bankingsystem.exceptions;

public class InsufficientFunds extends RuntimeException {

    public InsufficientFunds() {
        super("there are not enough funds to make the transfer");
    }
}
