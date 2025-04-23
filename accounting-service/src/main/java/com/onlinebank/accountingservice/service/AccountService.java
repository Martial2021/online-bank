package com.onlinebank.accountingservice.service;

import com.onlinebank.accountingservice.model.AccountingJournal;
import com.onlinebank.common.dto.TransactionDTO;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void credit(TransactionDTO dto);
    void debit(TransactionDTO dto);
    BigDecimal getBalance(String userId);
    List<AccountingJournal> getJournal(String userId);
}

;