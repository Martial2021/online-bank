package com.onlinebank.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    @NotNull
    private String userId;

    @NotNull
    private BigDecimal amount;
}
