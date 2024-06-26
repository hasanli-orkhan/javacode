package ru.domain.javacode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.domain.javacode.model.Wallet;

//import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findById(Long walletId); // TODO нет такого метода
}
