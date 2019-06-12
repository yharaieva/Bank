package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> findAllClients();

    void addClient(ClientDto clientDto);

    void addAccount(AccountDto accountDto);
}
