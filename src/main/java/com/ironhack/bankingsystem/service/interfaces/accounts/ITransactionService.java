package com.ironhack.bankingsystem.service.interfaces.accounts;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITransactionService {

    public void transferMoney(TransactionDTO transactiondto, UserDetails user);

    public void fraudDetectionInDay(Account account);

    public void fraudDetectionBySeconds(Account account);

}
