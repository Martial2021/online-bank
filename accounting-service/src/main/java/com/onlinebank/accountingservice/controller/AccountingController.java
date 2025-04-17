package com.onlinebank.accountingservice.controller;

import com.onlinebank.accountingservice.service.AccountService;
import com.onlinebank.common.dto.TransactionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
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
}
