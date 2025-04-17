package com.onlinebank.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String userId;
    private BigDecimal balance;
    private String currency;
}
