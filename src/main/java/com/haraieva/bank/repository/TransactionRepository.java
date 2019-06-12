package com.haraieva.bank.repository;

import com.haraieva.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

List<Transaction> findAllByCreatedTimeBetween(LocalDateTime from, LocalDateTime to);

@Query(value = "SELECT * " +
     "FROM transactions t " +
     "WHERE t.recipient_id = ?1 " +
     "OR t.sender_id = ?1", nativeQuery = true)
    List<Transaction> findAllByRecipientOrSender(Long clientId);
}
