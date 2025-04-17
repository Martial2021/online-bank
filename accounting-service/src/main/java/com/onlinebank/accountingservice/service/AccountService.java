package com.onlinebank.accountingservice.service;

import com.onlinebank.common.dto.TransactionDTO;

public interface AccountService {
    void credit(TransactionDTO dto);
    void debit(TransactionDTO dto);
}
