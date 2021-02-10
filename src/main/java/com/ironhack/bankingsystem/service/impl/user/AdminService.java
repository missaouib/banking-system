package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.accounts.*;
import com.ironhack.bankingsystem.dto.user.ThirdPartyDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.repository.user.ThirdPartyRepository;
import com.ironhack.bankingsystem.service.interfaces.user.IAdminService;
import com.ironhack.bankingsystem.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService implements IAdminService {
    private final AccountRepository accountRepository;
    private final SavingsRepository savingsRepository;
    private final CreditCardRepository creditCardRepository;
    private final CheckingRepository checkingRepository;
    private final AccountHolderRepository accountHolderRepository;
    private final StudentCheckingRepository studentCheckingRepository;
    private final ThirdPartyRepository thirdPartyRepository;

    @Autowired
    public AdminService( AccountRepository accountRepository, SavingsRepository savingsRepository, CreditCardRepository creditCardRepository, CheckingRepository checkingRepository, AccountHolderRepository accountHolderRepository, StudentCheckingRepository studentCheckingRepository, ThirdPartyRepository thirdPartyRepository) {
        this.accountRepository = accountRepository;
        this.savingsRepository = savingsRepository;
        this.creditCardRepository = creditCardRepository;
        this.checkingRepository = checkingRepository;
        this.accountHolderRepository = accountHolderRepository;
        this.studentCheckingRepository = studentCheckingRepository;
        this.thirdPartyRepository = thirdPartyRepository;
    }

    public Savings createSavingAccount(SavingDTO savingDto) {
        AccountHolder primaryOwner = getOwner(savingDto.getPrimaryOwnerId());

        AccountHolder secondaryOwner = null;
        if (savingDto.getSecondaryOwnerId() != null) {
            secondaryOwner = getOwner(savingDto.getSecondaryOwnerId());
        }
        Savings savings = new Savings(savingDto.getBalance(), primaryOwner, secondaryOwner, savingDto.getSecretKey());

        if (savingDto.getMinimumBalance() != null) {
            savings.setMinimumBalance(savingDto.getMinimumBalance());
        }
        if (savingDto.getInterestRate() != null) {
            savings.setInterestRate(savingDto.getInterestRate());
        }
        return savingsRepository.save(savings);
    }

    public CreditCard createCreditCardAccount(CreditCardDTO creditCardDto) {
        AccountHolder primaryOwner = getOwner(creditCardDto.getPrimaryOwnerId());

        AccountHolder secondaryOwner = null;
        if (creditCardDto.getSecondaryOwnerId() != null) {
            secondaryOwner = getOwner(creditCardDto.getSecondaryOwnerId());
        }

        CreditCard creditCard = new CreditCard(creditCardDto.getBalance(), primaryOwner, secondaryOwner);

        if (creditCardDto.getCreditLimit() != null) {
            creditCard.setCreditLimit(creditCardDto.getCreditLimit());
        }
        if (creditCardDto.getInterestRate() != null) {
            creditCard.setInterestRate(creditCardDto.getInterestRate());
        }
        return creditCardRepository.save(creditCard);
    }

    public Account createCheckingAccountOrCheckingStudent(CheckingDTO checkingDto) {
        AccountHolder primaryOwner = getOwner(checkingDto.getPrimaryOwnerId());

        AccountHolder secondaryOwner = null;
        if (checkingDto.getSecondaryOwnerId() != null) {
            secondaryOwner = getOwner(checkingDto.getSecondaryOwnerId());
        }

        if (primaryOwner.isOlderThan24()) {
            Checking checking = new Checking(checkingDto.getBalance(), primaryOwner, secondaryOwner, checkingDto.getSecretKey());

            if (checkingDto.getMinimumBalance() != null) {
                checking.setMinimumBalance(checkingDto.getMinimumBalance());
            }
            if (checkingDto.getMonthlyMaintenanceFee() != null) {
                checking.setMonthlyMaintenanceFee(checkingDto.getMonthlyMaintenanceFee());
            }
            return checkingRepository.save(checking);
        } else {
            StudentChecking studentChecking = new StudentChecking(checkingDto.getBalance(), primaryOwner, secondaryOwner, checkingDto.getSecretKey());
            return studentCheckingRepository.save(studentChecking);
        }
    }

    public void modifyBalance(Integer accountId, AmountDTO amountDto) {

        Account account = accountRepository.findById(accountId).orElseThrow(NoPresentAccount::new);

        if (amountDto.getAmount().signum() > 0) {
            account.getBalance().increaseAmount(amountDto.getAmount());
        } else {
            if (account.getBalance().getAmount().compareTo(amountDto.getAmount().negate()) > 0) {
                account.getBalance().decreaseAmount(amountDto.getAmount().negate());
            } else {
                throw new InsufficientFunds();
            }
        }
        accountRepository.save(account);
    }

    public void modifyStatus(Integer accountId, Status status) {
        Account account = accountRepository.findById(accountId).orElseThrow(NoPresentAccount::new);
        account.setStatus(status);
        accountRepository.save(account);
    }

    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartydto) {
        ThirdParty thirdParty = new ThirdParty(thirdPartydto.getUsername(), PasswordUtil.encode(thirdPartydto.getPassword()), thirdPartydto.getHashedKey());
        thirdParty.setRoles(Set.of(new Role("THIRDPARTY", thirdParty)));
        return thirdPartyRepository.save(thirdParty);
    }

    /**
     * Get owner by id
     * @param ownerId
     * @return
     */
    private AccountHolder getOwner(Integer ownerId) {
        return accountHolderRepository.findById(ownerId).orElseThrow(NoPresentAccountHolder::new);
    }

}
