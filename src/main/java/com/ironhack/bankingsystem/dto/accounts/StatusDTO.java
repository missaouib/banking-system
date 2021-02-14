package com.ironhack.bankingsystem.dto.accounts;

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
