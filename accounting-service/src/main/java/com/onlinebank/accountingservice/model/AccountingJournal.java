package com.onlinebank.accountingservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "accounting_journals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountingJournal {

    @Id
    private String id;

    private String accountId;
    private String userId;
    private String operation;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
