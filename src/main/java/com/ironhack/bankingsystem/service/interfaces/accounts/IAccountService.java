package com.ironhack.bankingsystem.service.interfaces.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IAccountService {

    public List<Account> findAll();

    public List<Account> findByStatus(Status status);

    public List<Account> viewAccountsByUsername(String username);

    public BalanceDTO checkBalance(Integer accountId, UserDetails user);

}
