package com.ironhack.bankingsystem.controller.impl.user;


import com.ironhack.bankingsystem.dto.accounts.*;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.Admin;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.user.AdminRepository;
import com.ironhack.bankingsystem.service.impl.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;


    @PostMapping("/v1/admin/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingAccount(@RequestBody @Valid SavingDTO savingdto) {
        return adminService.createSavingAccount(savingdto);
    }

    @PostMapping("v1/admin/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCardDTO creditCarddto) {
        return adminService.createCreditCardAccount(creditCarddto);
    }

    @PostMapping("v1/admin/checkingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingOrStudentCheckingAccount(@RequestBody @Valid CheckingDTO checkingDTO) {
        return adminService.createCheckingAccountOrCheckingStudent(checkingDTO);
    }

    @PostMapping("v1/admin/thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(@RequestBody @Valid ThirdPartyDTO thirdPartydto){
        return adminService.addThirdParty(thirdPartydto);
    }

    @PutMapping("v1/account/modify/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBalance (@PathVariable Integer id, @RequestBody AmountDTO Amountdto){
        adminService.modifyBalance(id,Amountdto);
    }

    @PatchMapping("v1/admin/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable Integer id, @RequestBody @Valid StatusDTO statusdto){
        adminService.modifyStatus(id, Status.valueOf(statusdto.getStatus().toUpperCase()));
    }
}
