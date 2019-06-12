package com.haraieva.bank.service;

import com.haraieva.bank.dto.TransactionDto;
import com.haraieva.bank.repository.TransactionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionDto> findAllByRecipientOrSender(Long accountId) {
        log.info("Get all transactions for client {}", accountId);
        return repository.findAllByRecipientOrSender(accountId).stream()
                .map(e -> new TransactionDto(e.getId(),
                        e.getRecipient() != null ? e.getRecipient().getId() : null,
                        e.getSender() != null ? e.getSender().getId() : null,
                        e.getAmount(),
                        e.getCreatedTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findAllByDateRange(LocalDateTime from, LocalDateTime to) {
        log.info("Get all transactions from {} and to {}", from, to);
        return repository.findAllByCreatedTimeBetween(from, to).stream().map(e -> new TransactionDto(e.getId(),
                e.getRecipient().getId(), e.getSender().getId(), e.getAmount(), e.getCreatedTime())).collect(Collectors.toList());
    }


}
