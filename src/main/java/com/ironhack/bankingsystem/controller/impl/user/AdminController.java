package com.ironhack.bankingsystem.controller.impl.user;


import com.ironhack.bankingsystem.controller.interfaces.user.IAdminController;
import com.ironhack.bankingsystem.dto.accounts.*;
import com.ironhack.bankingsystem.dto.user.ThirdPartyDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.user.AdminRepository;
import com.ironhack.bankingsystem.service.impl.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/admin")
public class AdminController implements IAdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingAccount(@RequestBody @Valid SavingDTO savingdto) {
        return adminService.createSavingAccount(savingdto);
    }

    @PostMapping("/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCardDTO creditCarddto) {
        return adminService.createCreditCardAccount(creditCarddto);
    }

    @PostMapping("/checkingaccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingOrStudentCheckingAccount(@RequestBody @Valid CheckingDTO checkingDTO) {
        return adminService.createCheckingAccountOrCheckingStudent(checkingDTO);
    }

    @PostMapping("/thirdparty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(@RequestBody @Valid ThirdPartyDTO thirdPartydto) {
        return adminService.addThirdParty(thirdPartydto);
    }

    @PutMapping("/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBalance(@PathVariable Integer id, @RequestBody AmountDTO Amountdto) {
        adminService.modifyBalance(id, Amountdto);
    }

    @PatchMapping("/account/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable Integer id, @RequestBody @Valid StatusDTO statusdto) {
        adminService.modifyStatus(id, Status.valueOf(statusdto.getStatus().toUpperCase()));
    }
}
