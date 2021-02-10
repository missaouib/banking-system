package com.ironhack.bankingsystem.controller.interfaces.accounts;


import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITransactionController {

    public void makeTransaction(UserDetails user, TransactionDTO transactiondto);

}
