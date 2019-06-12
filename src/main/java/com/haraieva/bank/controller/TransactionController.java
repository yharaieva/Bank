package com.haraieva.bank.controller;

import com.haraieva.bank.dto.TransactionDto;
import com.haraieva.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public String getTransactions(Model model,
                                  @RequestParam(value = "accountId", required = false) Long accountId,
                                  @RequestParam(value = "from", required = false) LocalDateTime from,
                                  @RequestParam(value = "to", required = false) LocalDateTime to) {
        if (accountId != null) {
            model.addAttribute("transactions", service.findAllByRecipientOrSender(accountId));
        } else if (from != null && to != null) {
            model.addAttribute("transactions", service.findAllByDateRange(from, to));
        } else {
            model.addAttribute("transactions", service.findAll());
        }

        return "transactions";
    }
}
