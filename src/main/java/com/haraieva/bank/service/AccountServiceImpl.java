package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.entity.Account;
import com.haraieva.bank.entity.Transaction;
import com.haraieva.bank.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
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
    public List<AccountDto> findAllByClientId(Long clientId) {
        log.info("Get all accounts by client {}", clientId);
        return repository.findAllByClientId(clientId).stream()
                .map(account -> new AccountDto(account.getId(), clientId, account.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void makeTransfer(Long senderId, Long recipientId, Double amount) {
        log.info("Making transfer from {} to {} with amount {}", senderId, recipientId, amount);

        if (senderId.equals(recipientId)) {
            log.error("Sender {} and recipient {} are the same accounts!", senderId, recipientId);
            return;
        }

        Optional<Account> senderAccount = repository.findById(senderId);
        Optional<Account> recipientAccount = repository.findById(recipientId);


        if (senderAccount.isPresent() && recipientAccount.isPresent()) {
            if (senderAccount.get().getAmount() < amount) {
                log.error("Sender {} has no enough money to make the transfer", senderId);
                return;
            }
            Account sender = senderAccount.get();
            Account recipient = recipientAccount.get();
            Transaction transaction = buildTransaction(amount);

            sender.addSentTransaction(transaction);
            recipient.addReceivedTransaction(transaction);

            repository.saveAll(Arrays.asList(sender, recipient));
        }
        else  {
            log.error("Unable to find sender {} or recipient {}", senderId, recipientId);
        }
    }

    @Override
    @Transactional
    public void withdrawMoney(Long accountId, Double amount) {
        log.info("Withdraw money {} from {}", amount, accountId);
        Optional<Account> account = repository.findById(accountId);

        if (account.isPresent()) {
            if (account.get().getAmount() < amount) {
                log.error("Account {} has no enough money to withdraw", accountId);
                return;
            }
            account.get().addSentTransaction(buildTransaction(amount));
            repository.save(account.get());
        }
        else {
            log.error("Unable to find account {}", accountId);
        }
    }

    @Override
    @Transactional
    public void topUpAccount(Long accountId, Double amount) {
        log.info("Top up account {} for amount {}", accountId, amount);
        Optional<Account> account = repository.findById(accountId);

        if (account.isPresent()) {
            account.get().addReceivedTransaction((buildTransaction(amount)));
            repository.save(account.get());
        }
        else {
            log.error("Unable to find account {}", accountId);
        }
    }

    private Transaction buildTransaction(Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        return transaction;
    }

}
