package com.ironhack.bankingsystem.handler;

import com.ironhack.bankingsystem.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AccountNoOwnerByName.class)
    public void accountNoOwnerByName(AccountNoOwnerByName accountNoOwnerByName, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accountNoOwnerByName.getMessage());
    }
    @ExceptionHandler(FrozenAccount.class)
    public void frozenAccount(FrozenAccount frozenAccount, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, frozenAccount.getMessage());
    }
    @ExceptionHandler(HashedKeyIncorrect.class)
    public void hashedKeyIncorrect(HashedKeyIncorrect hashedKeyIncorrect, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, hashedKeyIncorrect.getMessage());
    }

    @ExceptionHandler(InsufficientFunds.class)
    public void insufficientFunds(InsufficientFunds insufficientFunds, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, insufficientFunds.getMessage());
    }
    @ExceptionHandler(NoPresentAccount.class)
    public void noPresentAccount(NoPresentAccount noPresentAccount, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, noPresentAccount.getMessage());
    }
    @ExceptionHandler(NoPresentAccountHolder.class)
    public void noPresentAccountHolder(NoPresentAccountHolder noPresentAccountHolder, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, noPresentAccountHolder.getMessage());
    }
    @ExceptionHandler(NoPresentThirdParty.class)
    public void noPresentThirdParty(NoPresentThirdParty noPresentThirdParty, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, noPresentThirdParty.getMessage());
    }
    @ExceptionHandler(UnauthorizedAccess.class)
    public void unauthorizedAccess(UnauthorizedAccess unauthorizedAccess, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, unauthorizedAccess.getMessage());
    }
    @ExceptionHandler(UsernameNotFound.class)
    public void usernameNotFound(UsernameNotFound usernameNotFound, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, usernameNotFound.getMessage());
    }
}
