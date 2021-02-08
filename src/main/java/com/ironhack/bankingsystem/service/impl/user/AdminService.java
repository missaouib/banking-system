package com.ironhack.bankingsystem.service.impl.user;


import com.ironhack.bankingsystem.dto.accounts.CheckingDTO;
import com.ironhack.bankingsystem.dto.accounts.CreditCardDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.repository.accounts.CheckingRepository;
import com.ironhack.bankingsystem.repository.accounts.CreditCardRepository;
import com.ironhack.bankingsystem.repository.accounts.SavingsRepository;
import com.ironhack.bankingsystem.repository.accounts.StudentCheckingRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminService {

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

    public Savings createSavingAccount(SavingDTO savingdto){
        AccountHolder primaryOwner = accountHolderRepository
                .findById(savingdto.getPrimaryOwnerId())
                .orElseThrow(NoPresentAccountHolder::new);

        AccountHolder secondaryOwner = null;
        if(savingdto.getSecondaryOwnerId() != null) {
             secondaryOwner = accountHolderRepository.findById(savingdto.getSecondaryOwnerId())
                     .orElseThrow(NoPresentAccountHolder::new);
        }
        Savings savings = new Savings(savingdto.getBalance(),primaryOwner, secondaryOwner,savingdto.getSecretKey());

        if(savingdto.getMinimumBalance() != null){
            savings.setMinimumBalance(savingdto.getMinimumBalance());
        }
        if (savingdto.getInterestRate() != null){
            savings.setInterestRate(savingdto.getInterestRate());
        }
        return savingsRepository.save(savings);
    }

        public CreditCard createCreditCardAccount (CreditCardDTO creditCarddto){
            AccountHolder primaryOwner = accountHolderRepository
                    .findById(creditCarddto.getPrimaryOwnerId())
                    .orElseThrow(NoPresentAccountHolder::new);

            AccountHolder secondaryOwner = null;
            if(creditCarddto.getSecondaryOwnerId() != null) {
                secondaryOwner = accountHolderRepository.findById(creditCarddto.getSecondaryOwnerId())
                        .orElseThrow(NoPresentAccountHolder::new);
            }

        CreditCard creditCard = new CreditCard(creditCarddto.getBalance(),primaryOwner,secondaryOwner);

            if(creditCarddto.getCreditLimit() != null){
                creditCard.setCreditLimit(creditCarddto.getCreditLimit());
            }
            if (creditCarddto.getInterestRate() != null){
                creditCard.setInterestRate(creditCarddto.getInterestRate());
            }
            return creditCardRepository.save(creditCard);
        }

        public Account createCheckingAccountOrCheckingStudent(CheckingDTO checkingdto){
            AccountHolder primaryOwner = accountHolderRepository
                    .findById(checkingdto.getPrimaryOwnerId())
                    .orElseThrow(NoPresentAccountHolder::new);

            AccountHolder secondaryOwner = null;
            if(checkingdto.getSecondaryOwnerId() != null) {
                secondaryOwner = accountHolderRepository.findById(checkingdto.getSecondaryOwnerId())
                        .orElseThrow(NoPresentAccountHolder::new);
            }

            if (primaryOwner.isOlderThan24()){
                Checking checking = new Checking(checkingdto.getBalance(),primaryOwner,secondaryOwner,checkingdto.getSecretKey());

                if(checkingdto.getMinimumBalance() != null){
                    checking.setMinimumBalance(checkingdto.getMinimumBalance());
                }
                if (checkingdto.getMonthlyMaintenanceFee() != null){
                    checking.setMonthlyMaintenanceFee(checkingdto.getMonthlyMaintenanceFee());
                }
                return checkingRepository.save(checking);
            } else {
                StudentChecking studentChecking = new StudentChecking(checkingdto.getBalance(),primaryOwner,secondaryOwner,checkingdto.getSecretKey());
                return studentCheckingRepository.save(studentChecking);
            }
        }



}
