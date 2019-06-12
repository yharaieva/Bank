package com.haraieva.bank.controller;

import com.haraieva.bank.dto.ClientDto;
import com.haraieva.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public String getClients(Model model) {
        model.addAttribute("clients", service.findAllClients());
        return "clients";
    }

    @PostMapping("/addClient")
    public String addClient(@ModelAttribute("client") ClientDto clientDto) {
        service.addClient(clientDto);
        return "clients";
    }

    @GetMapping("/addClient")
    public String addClientView(Model model) {
        model.addAttribute("client", new ClientDto());
        return "addClient";
    }

}
