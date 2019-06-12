package com.haraieva.bank.service;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.dto.ClientDto;
import com.haraieva.bank.entity.Account;
import com.haraieva.bank.entity.Client;
import com.haraieva.bank.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientDto> findAllClients() {
        log.info("Get all clients");
        return repository.findAll().stream().map(e -> new ClientDto(e.getId(),
                e.getName(),e.getAdress(), e.getAge())).collect(Collectors.toList());
    }

    @Override
    public void addClient(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setAdress(clientDto.getAddress());
        client.setAge(clientDto.getAge());
        client.setAccounts(Collections.emptyList());
        repository.save(client);
    }

    @Override
    public void addAccount (AccountDto accountDto) {
        Optional<Client> client = repository.findById(accountDto.getClientId());

        if (client.isPresent()) {
            Account account = new Account();
            account.setAmount(accountDto.getAmount());
            client.get().addAccount(account);
            repository.save(client.get());
        }
        else {
            log.error("Unable to find client {}", accountDto.getClientId());
        }
    }
}
