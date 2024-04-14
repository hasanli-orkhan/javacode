package ru.domain.javacode.controller;

import ru.domain.javacode.controller.WalletController;
import ru.domain.javacode.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.domain.javacode.model.OperationType;
import ru.domain.javacode.model.WalletRequest;
import ru.domain.javacode.model.WalletResponse;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Test
    public void testDepositOperation() throws Exception {
        UUID walletId = UUID.randomUUID();
        WalletRequest request = new WalletRequest(walletId, OperationType.DEPOSIT, BigDecimal.valueOf(100.0));
        WalletResponse response = new WalletResponse("Operation successful", BigDecimal.valueOf(1100.0));

        when(walletService.processWalletOperation(any(WalletRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"walletId\":\"" + request.getWalletId() + "\",\"operationType\":\"" + request.getOperationType() + "\",\"amount\":\"" + request.getAmount() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Operation successful"))
                .andExpect(jsonPath("$.balance").value(1100.0));
    }

    @Test
    public void testWithdrawOperation() throws Exception {
        UUID walletId = UUID.randomUUID();
        WalletRequest request = new WalletRequest(walletId, OperationType.WITHDRAW, BigDecimal.valueOf(100.0));
        WalletResponse response = new WalletResponse("Withdrawal successful", BigDecimal.valueOf(900.0));

        when(walletService.processWalletOperation(any(WalletRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"walletId\":\"" + walletId + "\",\"operationType\":\"" + request.getOperationType() + "\",\"amount\":\"" + request.getAmount() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Withdrawal successful"))
                .andExpect(jsonPath("$.balance").value(900.0));
    }

    @Test
    public void testGetWalletBalance() throws Exception {
        UUID walletId = UUID.randomUUID();
        BigDecimal balance = BigDecimal.valueOf(1000.0);

        when(walletService.getWalletBalance(walletId)).thenReturn(balance);

        mockMvc.perform(get("/api/v1/wallets/{walletId}", walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.balance").value(1000.0));
    }
}