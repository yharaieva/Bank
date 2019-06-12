package com.haraieva.bank.repository;

import com.haraieva.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * " +
            "FROM accounts a " +
            "WHERE a.client_id = ?1 ", nativeQuery = true)
    List<Account> findAllByClientId(Long clientId);

}
