package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.exceptions.UnauthorizedAccess;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.service.interfaces.accounts.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;


    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findByStatus(Status status) {
        return accountRepository.findByStatus(status);
    }

    public List<Account> viewAccountsByUsername(String username) {
        AccountHolder owner = accountHolderRepository.findByUsername(username).orElseThrow(NoPresentAccountHolder::new);
        return accountRepository.findByPrimaryOwnerOrSecondaryOwner(owner, owner);

    }

    /**
     * service to check the balance
     */

    public BalanceDTO checkBalance(Integer accountId, UserDetails user) {
        Account account = accountRepository.findById(accountId).orElseThrow(NoPresentAccount::new);
        account.isOwnedBy(user.getUsername());

        if (account instanceof Checking) {
            return new BalanceDTO(account.getBalance());

        } else if (account instanceof Savings) {
            ((Savings) account).applyInterestRate();
            accountRepository.save(account);
            return new BalanceDTO(account.getBalance());

        } else if (account instanceof StudentChecking) {
            return new BalanceDTO(account.getBalance());

        } else {
            ((CreditCard) account).applyInterestRate();
            accountRepository.save(account);
            return new BalanceDTO(account.getBalance());
        }
    }
}
