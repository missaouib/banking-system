package com.ironhack.bankingsystem.controller.impl.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.user.ThirdPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ThirdPartyControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ThirdPartyService thirdPartyService;

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
        accountHolder.setRoles(Set.of(new Role("THIRDPARTY", accountHolder)));

    }

    @Test
    void thirdPartySendMoney_correct() throws Exception {
        ThirdPartyTransactionDTO thirdPartyTransactionDto = new ThirdPartyTransactionDTO(1,"456",2,new BigDecimal(100),"hola");

        String body = objectMapper.writeValueAsString(thirdPartyTransactionDto);
        MvcResult result = mockMvc
                .perform(put("/v1/thirdparty/sendmoney")
                        .content(body)
                        .header("Hashed-key","456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void thirdPartyReceiveMoney_correct() throws Exception {
        ThirdPartyTransactionDTO thirdPartyTransactionDto = new ThirdPartyTransactionDTO(1,"456",2,new BigDecimal(100),"hola");

        String body = objectMapper.writeValueAsString(thirdPartyTransactionDto);
        MvcResult result = mockMvc
                .perform(put("/v1/thirdparty/receivemoney")
                        .content(body)
                        .header("Hashed-key","456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isOk())
                .andReturn();
    }

}