package com.example.moneebackend.domain.transaction;

import com.example.moneebackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByUserId(Long userId); // JPA는 findBy + 필드명 패턴을 인식해서 자동 구현해준다.
    List<Transaction> findByUserAndDateBetween(User user, LocalDateTime start, LocalDateTime end);
    Optional<Transaction> findByIdAndUser(Long id, User user);
}
