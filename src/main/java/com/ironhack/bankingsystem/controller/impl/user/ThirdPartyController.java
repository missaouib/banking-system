package com.ironhack.bankingsystem.controller.impl.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import com.ironhack.bankingsystem.service.impl.user.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;


    @PutMapping("v1/thirdParty/sendmoney")
    @ResponseStatus(HttpStatus.OK)
    public void thirdPartySendMoney(@RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactiondto, @RequestHeader("Hashed-key") String hashedKey){
        thirdPartyTransactiondto.setHashedKey(hashedKey);
        thirdPartyService.sendMoney(thirdPartyTransactiondto);
    }

    @PutMapping("v1/thirdParty/receivemoney")
    @ResponseStatus(HttpStatus.OK)
    public void thirdPartyReceiveMoney(@RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactiondto, @RequestHeader("Hashed-key") String hashedKey){
        thirdPartyTransactiondto.setHashedKey(hashedKey);
        thirdPartyService.receiveMoney(thirdPartyTransactiondto);
    }


}
