package com.ironhack.bankingsystem.controller.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;


    @MockBean
    private AccountService accountService;

    private MockMvc mockMvc;
    private AccountHolder accountHolder;
    private Account account;
    private BalanceDTO balanceDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        account = new Checking(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        account.setCreationDate(LocalDate.of(2021, 2, 13));
        balanceDto = new BalanceDTO(new Money(new BigDecimal(2000)));
        accountHolder.setRoles(Set.of(new Role("ACCOUNTHOLDER", accountHolder)));
    }

    @Test
    void findAll_correct() throws Exception {
        String expectedResponse = "[{\"id\":null,\"balance\":{\"currency\":\"USD\",\"amount\":2000.00},\"penaltyFee\":40,\"creationDate\":\"2021-02-13\",\"status\":\"ACTIVE\",\"secretKey\":\"algo\",\"minimumBalance\":250,\"monthlyMaintenanceFee\":12}]";
        when(accountService.findAll()).thenReturn(List.of(account));

        MvcResult result = mockMvc
                .perform(get("/v1/accounts")
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, result.getResponse().getContentAsString());
    }

    @Test
    void findByOwnerId_Correct() throws Exception {

        String expectedResponse = "[{\"id\":null,\"balance\":{\"currency\":\"USD\",\"amount\":2000.00},\"penaltyFee\":40,\"creationDate\":\"2021-02-13\",\"status\":\"ACTIVE\",\"secretKey\":\"algo\",\"minimumBalance\":250,\"monthlyMaintenanceFee\":12}]";
        when(accountService.viewAccountsById(1)).thenReturn(List.of(account));

        MvcResult result = mockMvc
                .perform(get("/v1/accounts/1")
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, result.getResponse().getContentAsString());

    }

    @Test
    void findByStatus_correct() throws Exception {

        String expectedResponse = "[{\"id\":null,\"balance\":{\"currency\":\"USD\",\"amount\":2000.00},\"penaltyFee\":40,\"creationDate\":\"2021-02-13\",\"status\":\"ACTIVE\",\"secretKey\":\"algo\",\"minimumBalance\":250,\"monthlyMaintenanceFee\":12}]";
        when(accountService.findByStatus(Status.ACTIVE)).thenReturn(List.of(account));

        MvcResult result = mockMvc
                .perform(get("/v1/accounts/active/status")
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, result.getResponse().getContentAsString());

    }

    @Test
    void accessBalanceByAccountId_Correct() throws Exception {
        String expectedResponse = "{\"balance\":{\"currency\":\"USD\",\"amount\":2000.00}}";
        when(accountService.checkBalance(1, new CustomUserDetails(accountHolder))).thenReturn((balanceDto));

        MvcResult result = mockMvc
                .perform(get("/v1/account/1/balance")
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, result.getResponse().getContentAsString());
    }
}