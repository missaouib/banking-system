package com.ironhack.bankingsystem.exceptions;

public class AccountNoOwnerByName extends RuntimeException{

    public AccountNoOwnerByName() {
        super("the name does not match the account owner ");
    }
}
