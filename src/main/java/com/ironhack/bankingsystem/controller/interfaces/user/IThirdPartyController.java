package com.ironhack.bankingsystem.controller.interfaces.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IThirdPartyController {

    public void thirdPartySendMoney(UserDetails user, ThirdPartyTransactionDTO thirdPartyTransactiondto, String hashedKey);

    public void thirdPartyReceiveMoney(UserDetails user, ThirdPartyTransactionDTO thirdPartyTransactiondto, String hashedKey);
}
