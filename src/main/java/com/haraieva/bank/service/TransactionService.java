package com.haraieva.bank.service;

import com.haraieva.bank.dto.TransactionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    List<TransactionDto> findAllByRecipientOrSender(Long accountId);

    List<TransactionDto> findAllByDateRange(LocalDateTime from, LocalDateTime to);
}
