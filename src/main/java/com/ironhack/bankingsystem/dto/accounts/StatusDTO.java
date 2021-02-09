package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.enums.Status;

import javax.validation.constraints.NotNull;

public class StatusDTO {

    @NotNull
    private String status;

    public StatusDTO() {
    }

    public StatusDTO(@NotNull String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
