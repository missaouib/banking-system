package com.ironhack.bankingsystem.controller.impl.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingsystem.dto.accounts.CheckingDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.user.AdminService;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AdminService adminService;

    private MockMvc mockMvc;
    private AccountHolder accountHolder;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        accountHolder.setRoles(Set.of(new Role("ADMIN", accountHolder)));
    }

    @Test
    void createSavingAccount_correct() throws Exception {
        String expected = "{\"id\":null,\"balance\":{\"currency\":\"USD\",\"amount\":1000.00},\"penaltyFee\":40,\"creationDate\":\"2020-02-13\",\"status\":\"ACTIVE\",\"secretKey\":\"hola\",\"minimumBalance\":1000,\"interestRate\":0.0025,\"paidInterestRate\":\"2021-02-13\"}";
        Savings accountSaving = new Savings(new Money(new BigDecimal(1000)), accountHolder, null, "hola");
        accountSaving.setCreationDate(LocalDate.of(2020,2,13));
        SavingDTO savingDTO = new SavingDTO(new Money(new BigDecimal(1000)),1,null,"hola",null,null);

        when(adminService.createSavingAccount(savingDTO)).thenReturn(accountSaving);
        String body = objectMapper.writeValueAsString(savingDTO);
        MvcResult result = mockMvc
                .perform(post("/v1/admin/saving")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(expected, result.getResponse().getContentAsString());
    }


    @Test
    void createCheckingAccount_correct() throws Exception {
        String expected = "{\"id\":null,\"balance\":{\"currency\":\"USD\",\"amount\":1000.00},\"penaltyFee\":40,\"creationDate\":\"2020-02-13\",\"status\":\"ACTIVE\",\"secretKey\":\"hola\",\"minimumBalance\":250,\"monthlyMaintenanceFee\":12}";
        Checking accountChecking = new Checking(new Money(new BigDecimal(1000)), accountHolder, null, "hola");
        accountChecking.setCreationDate(LocalDate.of(2020,2,13));
        CheckingDTO checkingDTO = new CheckingDTO(new Money(new BigDecimal(1000)),1,null,"hola",null,null);

        when(adminService.createCheckingAccountOrCheckingStudent(checkingDTO)).thenReturn(accountChecking);
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result = mockMvc
                .perform(post("/v1/admin/checking")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(expected, result.getResponse().getContentAsString());
    }
}