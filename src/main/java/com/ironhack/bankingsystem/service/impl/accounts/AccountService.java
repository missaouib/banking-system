package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;


    public BalanceDTO checkBalance(Integer accountId) {

        Account account = accountRepository.findById(accountId).orElseThrow(NoPresentAccount::new);

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
