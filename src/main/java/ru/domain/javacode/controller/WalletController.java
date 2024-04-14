package ru.domain.javacode.controller;

import org.springframework.http.ResponseEntity;
import ru.domain.javacode.exception.InsufficientFundsException;
import ru.domain.javacode.exception.WalletNotFoundException;
import ru.domain.javacode.model.WalletRequest;
import ru.domain.javacode.model.WalletResponse;
import ru.domain.javacode.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletResponse> getWalletBalance(@PathVariable UUID walletId) {
        try {
            BigDecimal balance = walletService.getWalletBalance(walletId);
            return ResponseEntity.ok(new WalletResponse("Success", balance));
        } catch (WalletNotFoundException ex) {
            return ResponseEntity.badRequest().body(new WalletResponse(ex.getMessage()));
        }
    }

    @PostMapping("/wallet")
    public ResponseEntity<WalletResponse> processWalletOperation(@RequestBody WalletRequest request) {
        try {
            WalletResponse response = walletService.processWalletOperation(request);
            return ResponseEntity.ok(response);
        } catch (WalletNotFoundException | InsufficientFundsException ex) {
            return ResponseEntity.badRequest().body(new WalletResponse(ex.getMessage()));
        }
    }


}
