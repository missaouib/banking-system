package com.ironhack.bankingsystem.exceptions;

public class InsufficientFunds extends RuntimeException {

    public InsufficientFunds() {
        super("the operation cannot be carried out, there are not enough funds in the account");
    }
}
