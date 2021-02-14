package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.enums.Status;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusDTO statusDTO = (StatusDTO) o;
        return Objects.equals(status, statusDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
