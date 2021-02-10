package com.ironhack.bankingsystem.service.interfaces.user;

import com.ironhack.bankingsystem.dto.accounts.AmountDTO;
import com.ironhack.bankingsystem.dto.accounts.CheckingDTO;
import com.ironhack.bankingsystem.dto.accounts.CreditCardDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.dto.user.ThirdPartyDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.ThirdParty;

public interface IAdminService {
    public Savings createSavingAccount(SavingDTO savingdto);

    public CreditCard createCreditCardAccount(CreditCardDTO creditCarddto);

    public Account createCheckingAccountOrCheckingStudent(CheckingDTO checkingdto);

    public void modifyBalance(Integer accountId, AmountDTO amountdto);

    public void modifyStatus(Integer accountId, Status status);

    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartydto);
}
