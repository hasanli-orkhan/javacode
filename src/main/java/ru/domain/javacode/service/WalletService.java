package ru.domain.javacode.service;

import org.springframework.stereotype.Service;
import ru.domain.javacode.exception.InsufficientFundsException;
import ru.domain.javacode.exception.WalletNotFoundException;
import ru.domain.javacode.model.OperationType;
import ru.domain.javacode.model.Wallet;
import ru.domain.javacode.model.WalletRequest;
import ru.domain.javacode.model.WalletResponse;
import ru.domain.javacode.repository.WalletRepository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public WalletResponse processWalletOperation(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        BigDecimal updatedBalance;
        if (request.getOperationType() == OperationType.DEPOSIT) {
            updatedBalance = wallet.getBalance().add(request.getAmount());
        } else if (request.getOperationType() == OperationType.WITHDRAW) {
            if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            updatedBalance = wallet.getBalance().subtract(request.getAmount());
        } else {
            throw new IllegalArgumentException("Invalid operation type");
        }

        wallet.setBalance(updatedBalance);
        walletRepository.save(wallet);

        return new WalletResponse("Operation successful", updatedBalance);
    }

    public BigDecimal getWalletBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }
}
