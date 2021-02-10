package com.ironhack.bankingsystem.controller.impl.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import com.ironhack.bankingsystem.model.user.User;
import com.ironhack.bankingsystem.service.impl.user.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/thirdparty")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;


    @PutMapping("/sendmoney")
    @ResponseStatus(HttpStatus.OK)
    public void thirdPartySendMoney(@AuthenticationPrincipal UserDetails user, @RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactiondto, @RequestHeader("Hashed-key") String hashedKey){
        thirdPartyTransactiondto.setHashedKey(hashedKey);
        thirdPartyService.sendMoney(thirdPartyTransactiondto, user);
    }

    @PutMapping("/receivemoney")
    @ResponseStatus(HttpStatus.OK)
    public void thirdPartyReceiveMoney(@AuthenticationPrincipal UserDetails user, @RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactiondto, @RequestHeader("Hashed-key") String hashedKey){
        thirdPartyTransactiondto.setHashedKey(hashedKey);
        thirdPartyService.receiveMoney(thirdPartyTransactiondto,user);
    }


}
