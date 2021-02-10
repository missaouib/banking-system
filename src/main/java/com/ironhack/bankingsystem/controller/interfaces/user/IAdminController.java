package com.ironhack.bankingsystem.controller.interfaces.user;

import com.ironhack.bankingsystem.dto.accounts.*;
import com.ironhack.bankingsystem.dto.user.ThirdPartyDTO;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


public interface IAdminController {

    public Savings createSavingAccount( SavingDTO savingdto);
    public CreditCard createCreditCardAccount(CreditCardDTO creditCarddto);
    public Account createCheckingOrStudentCheckingAccount(CheckingDTO checkingDTO);
    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartydto);
    public void modifyBalance( Integer id,  AmountDTO Amountdto);
    public void changeStatus( Integer id, StatusDTO statusdto);



}
