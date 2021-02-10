package com.ironhack.bankingsystem.service.interfaces.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IThirdPartyService {

    public void sendMoney(ThirdPartyTransactionDTO thirdPartyTransactiondto, UserDetails user);

    public void receiveMoney(ThirdPartyTransactionDTO thirdPartyTransactiondto, UserDetails user);


}
