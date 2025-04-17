package com.onlinebank.accountingservice.repository;

import com.onlinebank.accountingservice.model.AccountingJournal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountingJournalRepository extends MongoRepository<AccountingJournal, String> {
    List<AccountingJournal> findByUserId(String userId);
}
