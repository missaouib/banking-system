package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.accounts.AmountDTO;
import com.ironhack.bankingsystem.dto.accounts.CheckingDTO;
import com.ironhack.bankingsystem.dto.accounts.CreditCardDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.dto.user.ThirdPartyDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.Role;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.repository.user.ThirdPartyRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import com.ironhack.bankingsystem.utils.Money;
import com.ironhack.bankingsystem.utils.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminServiceTest {
    @MockBean
    private  AccountRepository accountRepository;
    @MockBean
    private  SavingsRepository savingsRepository;
    @MockBean
    private  CreditCardRepository creditCardRepository;
    @MockBean
    private  CheckingRepository checkingRepository;
    @MockBean
    private  AccountHolderRepository accountHolderRepository;
    @MockBean
    private  StudentCheckingRepository studentCheckingRepository;
    @MockBean
    private  ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private AdminService adminService;

    private Checking accountChecking;
    private StudentChecking accountStudent;
    private CreditCard accountCreditCard;
    private Savings accountSaving;
    private AccountHolder accountHolder;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountChecking = new Checking(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        accountStudent = new StudentChecking(new Money(new BigDecimal(1500)), accountHolder, null, "hola");
        accountCreditCard = new CreditCard(new Money(new BigDecimal(2000)),accountHolder,null);
        accountSaving = new Savings(new Money(new BigDecimal(500)), accountHolder, null, "hola");
        customUserDetails = new CustomUserDetails(accountHolder);
    }

    @Test
    void createSavingAccount_defaultValues() {
        SavingDTO savingDto = new SavingDTO(new Money(new BigDecimal(500)),1,null,"hola",null,null);
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(savingsRepository.save(accountSaving)).thenReturn(accountSaving);

        Savings savingsResponse = adminService.createSavingAccount(savingDto);

        assertEquals(accountSaving, savingsResponse);
    }

    @Test
    void createSavingAccount_withValuesNoDefault() {
        Savings savings = new Savings(new Money(new BigDecimal(500)),accountHolder,accountHolder,"hola");
        savings.setMinimumBalance(new BigDecimal(500));
        savings.setInterestRate(new BigDecimal(0.05));
        SavingDTO savingDto = new SavingDTO(new Money(new BigDecimal(500)),1,1,"hola",new BigDecimal(500),new BigDecimal(0.05));
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(savingsRepository.save(savings)).thenReturn(savings);

        Savings savingsResponse = adminService.createSavingAccount(savingDto);

        assertEquals(savings, savingsResponse);
    }
    @Test
    void createCreditAccount_defaultValues() {
        CreditCardDTO creditCardDto = new CreditCardDTO(new Money(new BigDecimal(2000)),1,null,null,null);
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(creditCardRepository.save(accountCreditCard)).thenReturn(accountCreditCard);

        CreditCard creditCardResponse = adminService.createCreditCardAccount(creditCardDto);

        assertEquals(accountCreditCard, creditCardResponse);
    }
    @Test
    void createCreditAccount_withValuesNoDefault(){
       CreditCard creditCard = new CreditCard (new Money(new BigDecimal(2000)),accountHolder,accountHolder);
       creditCard.setCreditLimit(new BigDecimal(200));
       creditCard.setInterestRate(new BigDecimal(0.15));
       CreditCardDTO  creditCardDTO = new CreditCardDTO(new Money(new BigDecimal(2000)),1,1,new BigDecimal(200),new BigDecimal(0.15));
       when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
       when(creditCardRepository.save(creditCard)).thenReturn(creditCard);

       CreditCard creditCardResponse= adminService.createCreditCardAccount(creditCardDTO);

       assertEquals(creditCard, creditCardResponse);

    }

    @Test
    void createCheckingAccount_defaultValues() {
        CheckingDTO checkingDto = new CheckingDTO(new Money(new BigDecimal(2000)),1,null,"algo",null,null);
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(checkingRepository.save(accountChecking)).thenReturn(accountChecking);

        Checking checkingResponse = (Checking) adminService.createCheckingAccountOrCheckingStudent(checkingDto);

        assertEquals(accountChecking,checkingResponse);

    }
    @Test
    void createCheckingAccount_withValuesNoDefault() {
     Checking checking = new Checking(new Money(new BigDecimal(2000)),accountHolder,accountHolder,"algo");
     checking.setMinimumBalance(new BigDecimal(1500));
     checking.setMonthlyMaintenanceFee(new BigDecimal(20));
     CheckingDTO checkingDto = new CheckingDTO((new Money(new BigDecimal(2000))),1,1, "algo",new BigDecimal(1500),new BigDecimal(20));
     when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
     when(checkingRepository.save(checking)).thenReturn(checking);

     Account checkingResponse = adminService.createCheckingAccountOrCheckingStudent(checkingDto);

     assertEquals(checking,checkingResponse);
    }
    @Test
    void createCheckingStudentAccount() {
      accountHolder.setDateOfBirth(LocalDate.of(2000,4,23));
        CheckingDTO checkingDto = new CheckingDTO(new Money(new BigDecimal(1500)),1,null,"hola",null,null);
      when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
      when(studentCheckingRepository.save(accountStudent)).thenReturn(accountStudent);

      Account studentCheckingResponse = adminService.createCheckingAccountOrCheckingStudent(checkingDto);

      assertEquals(accountStudent,studentCheckingResponse);
    }

    @Test
    void modifyBalance_PositiveAmount_incrementBalance() {
        AmountDTO amountDto = new AmountDTO(new BigDecimal(100));
       when(accountRepository.findById(1)).thenReturn(Optional.of(accountChecking));

       adminService.modifyBalance(1,amountDto);
       accountChecking.setBalance(new Money(new BigDecimal(2100)));

       verify(accountRepository).save(accountChecking);
    }

    @Test
    void modifyBalance_NegativeAmount_decrementBalance() {

        AmountDTO amountDto = new AmountDTO(new BigDecimal(-100));
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountChecking));

        adminService.modifyBalance(1,amountDto);
        accountChecking.setBalance(new Money(new BigDecimal(1900)));

        verify(accountRepository).save(accountChecking);

    }
    @Test
    void modifyBalance_InsufficientFunds_exception() {
        AmountDTO amountDto = new AmountDTO(new BigDecimal(-2100));
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountChecking));

       assertThrows(InsufficientFunds.class, ()-> adminService.modifyBalance(1,amountDto));
    }

    @Test
    void modifyStatus_correct() {
        Checking checking = new Checking(new Money(new BigDecimal(1900)),accountHolder,null,"algo");
        checking.setStatus(Status.FROZEN);
        when(accountRepository.findById(1)).thenReturn(Optional.of(checking));

        adminService.modifyStatus(1,Status.ACTIVE);
        checking.setStatus(Status.ACTIVE);

        verify(accountRepository).save(checking);
    }

    @Test
    void addThirdParty() {
        ThirdPartyDTO thirdPartyDto = new ThirdPartyDTO("123456","sergio","123456");
        ThirdParty thirdParty = new ThirdParty("sergio", PasswordUtil.encode("123456"),"123456");
        thirdParty.setRoles(Set.of(new Role("THIRDPARTY", thirdParty)));
        when(thirdPartyRepository.save(thirdParty)).thenReturn(thirdParty);

        ThirdParty thirdPartyResponse = adminService.addThirdParty(thirdPartyDto);

        assertEquals(thirdParty,thirdPartyResponse);

    }
}