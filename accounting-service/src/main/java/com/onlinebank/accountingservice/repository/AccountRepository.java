package com.onlinebank.accountingservice.repository;

import com.onlinebank.accountingservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUserId(String userId);
}
