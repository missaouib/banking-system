package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.exceptions.UsernameNotFound;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.User;
import com.ironhack.bankingsystem.repository.user.UserRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername_CORRECT() {
        User accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        when(userRepository.findByUsername("nerea")).thenReturn(Optional.of(accountHolder));

        UserDetails userDetails= customUserDetailsService.loadUserByUsername("nerea");

        assertEquals(new CustomUserDetails(accountHolder),userDetails);

    }
    @Test
    void loadUserByUsername_Exception() {
        User accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        when(userRepository.findByUsername("nerea")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFound.class,()->customUserDetailsService.loadUserByUsername("sergio"));

    }



}