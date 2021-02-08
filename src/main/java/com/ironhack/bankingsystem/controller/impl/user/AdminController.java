package com.ironhack.bankingsystem.controller.impl.user;


import com.ironhack.bankingsystem.dto.accounts.CheckingDTO;
import com.ironhack.bankingsystem.dto.accounts.CreditCardDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.service.impl.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/v1/admin/create/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingAccount(@RequestBody @Valid SavingDTO savingdto) {
        return adminService.createSavingAccount(savingdto);
    }

    @PostMapping("v1/admin/create/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCardDTO creditCarddto) {
        return adminService.createCreditCardAccount(creditCarddto);
    }

    @PostMapping("v1/admin/create/checkingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingOrStudentCheckingAccount(@RequestBody @Valid CheckingDTO checkingDTO) {
        return adminService.createCheckingAccountOrCheckingStudent(checkingDTO);
    }


}
