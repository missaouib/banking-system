package com.ironhack.bankingsystem.service.impl.user;


import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.repository.accounts.SavingsRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public Savings createSavingAccount(SavingDTO savingdto){
        AccountHolder primaryOwner = accountHolderRepository
                .findById(savingdto.getPrimaryOwnerId())
                .orElseThrow(NoPresentAccountHolder::new);

        AccountHolder secondaryOwner = null;
        if(savingdto.getSecondaryOwnerId() != null) {
             secondaryOwner = accountHolderRepository.findById(savingdto.getSecondaryOwnerId())
                     .orElseThrow(NoPresentAccountHolder::new);
        }
        Savings savings = new Savings(savingdto.getBalance(),primaryOwner, secondaryOwner,savingdto.getPenaltyFee(),savingdto.getSecretKey());

        if(savingdto.getMinimumBalance() != null){
            savings.setMinimumBalance(savingdto.getMinimumBalance());
        }
        if (savingdto.getInterestRate() != null){
            savings.setInterestRate(savingdto.getInterestRate());
        }
        return savingsRepository.save(savings);
    }






}
