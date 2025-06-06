package com.onlinebank.accountingservice.service;

import com.onlinebank.accountingservice.model.Account;
import com.onlinebank.accountingservice.model.AccountingJournal;
import com.onlinebank.accountingservice.repository.AccountRepository;
import com.onlinebank.accountingservice.repository.AccountingJournalRepository;
import com.onlinebank.common.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountingServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountingJournalRepository journalRepository;

    @Override
    public void credit(TransactionDTO dto) {
        Account account = accountRepository.findByUserId(dto.getUserId())
                .orElseGet(() -> {
                    Account newAccount = new Account();
                    newAccount.setUserId(dto.getUserId());
                    newAccount.setBalance(BigDecimal.ZERO);
                    return accountRepository.save(newAccount);
                });

        account.setBalance(account.getBalance().add(dto.getAmount()));
        accountRepository.save(account);

        journalRepository.save(AccountingJournal.builder()
                .accountId(account.getId())
                .userId(dto.getUserId())
                .operation("CREDIT")
                .amount(dto.getAmount())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @Override
    public void debit(TransactionDTO dto) {
        Account account = accountRepository.findByUserId(dto.getUserId())
                .orElseGet(() -> {
                    Account newAccount = new Account();
                    newAccount.setUserId(dto.getUserId());
                    newAccount.setBalance(BigDecimal.ZERO);
                    return accountRepository.save(newAccount);
                });

        if (account.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new RuntimeException("Solde insuffisant");
        }

        account.setBalance(account.getBalance().subtract(dto.getAmount()));
        accountRepository.save(account);

        journalRepository.save(AccountingJournal.builder()
                .accountId(account.getId())
                .userId(dto.getUserId())
                .operation("DEBIT")
                .amount(dto.getAmount())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @Override
    public BigDecimal getBalance(String userId) {
        return accountRepository.findByUserId(userId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Aucun compte trouvé pour l'utilisateur : " + userId));
    }

    @Override
    public List<AccountingJournal> getJournal(String userId) {
        return journalRepository.findByUserId(userId);
    }

}
