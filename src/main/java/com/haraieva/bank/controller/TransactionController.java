package com.haraieva.bank.controller;

import com.haraieva.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public String getTransactions(Model model, @RequestParam(value = "accountId", required = false) Long accountId,
                                  @RequestParam(value = "from", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                  @RequestParam(value = "to", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
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

