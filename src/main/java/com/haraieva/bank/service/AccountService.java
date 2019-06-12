package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.entity.Account;

import java.util.List;

public interface AccountService {

    List<AccountDto> findAllByClientId(Long clientId);

    void makeTransfer(Long senderId, Long recipientId, Double amount);

    void withdrawMoney(Long accountId, Double amount);

    void topUpAccount(Long accountId, Double ammount);
}
