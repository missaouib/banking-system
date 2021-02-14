package com.ironhack.bankingsystem.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingsystem.dto.accounts.StatusDTO;
import com.ironhack.bankingsystem.exceptions.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.user.AdminService;
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

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApiExceptionHandlerTest {

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
    void accountNoOwnerByName() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(AccountNoOwnerByName.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void frozenAccount() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(FrozenAccount.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isPreconditionFailed())
                .andReturn();
    }

    @Test
    void hashedKeyIncorrect() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(HashedKeyIncorrect.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void insufficientFunds() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(InsufficientFunds.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isPreconditionFailed())
                .andReturn();
    }

    @Test
    void noPresentAccount() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(NoPresentAccount.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void noPresentAccountHolder() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(NoPresentAccountHolder.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void noPresentThirdParty() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(NoPresentThirdParty.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isPreconditionFailed())
                .andReturn();
    }

    @Test
    void unauthorizedAccess() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(UnauthorizedAccess.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void usernameNotFound() throws Exception {
        StatusDTO statusDto = new StatusDTO("frozen");
        doThrow(UsernameNotFound.class).when(adminService).modifyStatus(any(),any());

        String body = objectMapper.writeValueAsString(statusDto);
        mockMvc.perform(patch("/v1/admin/account/1/status")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(new CustomUserDetails(accountHolder))))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}