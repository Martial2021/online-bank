package com.onlinebank.accountingservice.controller;

import com.onlinebank.accountingservice.model.AccountingJournal;
import com.onlinebank.accountingservice.service.AccountService;
import com.onlinebank.common.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@SecurityRequirement(name = "BearerAuth")
@RequiredArgsConstructor
public class AccountingController {

    private final AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@Valid @RequestBody TransactionDTO dto) {
        accountService.credit(dto);
        return ResponseEntity.ok("Compte crédité avec succès.");
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@Valid @RequestBody TransactionDTO dto) {
        accountService.debit(dto);
        return ResponseEntity.ok("Compte débité avec succès.");
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(accountService.getBalance(userId));
    }

    @GetMapping("/journal")
    public ResponseEntity<List<AccountingJournal>> getJournal(Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(accountService.getJournal(userId));
    }

}
