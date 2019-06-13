package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.dto.TransferRequest;
import com.haraieva.bank.entity.Account;
import com.haraieva.bank.entity.Transaction;
import com.haraieva.bank.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<AccountDto> findAllAccounts() {
        log.info("Get all accounts");
        return repository.findAll().stream()
                .map(account -> new AccountDto(account.getId(), account.getClient().getId(),
                        account.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AccountDto> findAllByClientId(Long clientId) {
        log.info("Get all accounts by client {}", clientId);
        return repository.findAllByClientId(clientId).stream()
                .map(account -> new AccountDto(account.getId(), clientId, account.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void transfer(TransferRequest request) {
        log.info("Making transfer from {} to {} with amount {}", request.getSenderId(),
                request.getRecipientId(), request.getAmount());

        if (request.getSenderId() != null && request.getRecipientId() != null) {
            makeTransfer(request);
        } else if (request.getSenderId() != null) {
            withdrawMoney(request.getSenderId(), request.getAmount());
        } else if (request.getRecipientId() != null) {
            topUpAccount(request.getRecipientId(), request.getAmount());
        } else {
            log.error("Invalid transfer request {}", request);
        }
    }

    private void makeTransfer(TransferRequest request) {
        if (request.getSenderId().equals(request.getRecipientId())) {
            log.error("Sender {} and recipient {} are the same accounts!", request.getSenderId(),
                    request.getRecipientId());
            return;
        }

        Optional<Account> senderAccount = repository.findById(request.getSenderId());
        Optional<Account> recipientAccount = repository.findById(request.getRecipientId());

        if (senderAccount.isPresent() && recipientAccount.isPresent()) {
            if (senderAccount.get().getAmount() < request.getAmount()) {
                log.error("Sender {} has no enough money to make transfer", request.getSenderId());
                return;
            }

            Account sender = senderAccount.get();
            Account recipient = recipientAccount.get();
            Transaction transaction = buildTransaction(request.getAmount());

            sender.addSentTransaction(transaction);
            recipient.addReceivedTransaction(transaction);
        } else {
            log.error("Unable to find sender {} or recipient {}", request.getSenderId(),
                    request.getRecipientId());
        }
    }

    private void withdrawMoney(Long accountId, Double amount) {
        log.info("Withdraw money {} from account {}", amount, accountId);
        Optional<Account> account = repository.findById(accountId);

        if (account.isPresent()) {
            if (account.get().getAmount() < amount) {
                log.error("Account {} has no enough money to withdraw", accountId);
                return;
            }
            account.get().addSentTransaction(buildTransaction(amount));
        } else {
            log.error("Unable to find account {}", accountId);
        }
    }

    private void topUpAccount(Long accountId, Double amount) {
        log.info("Top up account {} for {} ", accountId, amount);
        Optional<Account> account = repository.findById(accountId);

        if (account.isPresent()) {
            account.get().addReceivedTransaction(buildTransaction(amount));
        } else {
            log.error("Unable to find account {}", accountId);
        }
    }

    private Transaction buildTransaction(Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCreatedTime(LocalDateTime.now());
        return transaction;
    }
}
