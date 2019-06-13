package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.dto.TransferRequest;
import com.haraieva.bank.entity.Account;

import java.util.List;

public interface AccountService {

    List<AccountDto> findAllAccounts();

    List<AccountDto> findAllByClientId(Long clientId);

    void transfer(TransferRequest request);

}
