package com.haraieva.bank.controller;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.service.AccountService;
import com.haraieva.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountController(AccountService accountService,
                             ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping
    public String getAccountsByClientId(Model model,
                                        @RequestParam(value = "clientId") Long clientId) {
        model.addAttribute("accounts", accountService.findAllByClientId(clientId));
        return "accounts";
    }

    @PostMapping("/addAccount")
    public String addAccount(@ModelAttribute("account") AccountDto accountDto) {
        clientService.addAccount(accountDto);
        return "accounts";
    }

    @GetMapping("/addAccount")
    public String addAccountView(Model model) {
        model.addAttribute("account", new AccountDto());
        return "addAccount";
    }

    @GetMapping("/transfer")
    public void makeTransfer(
            @RequestParam(value = "senderId") Long senderId,
            @RequestParam(value = "recipientId") Long recipientId,
            @RequestParam(value = "amount") Double amount) {
        accountService.makeTransfer(senderId, recipientId, amount);
    }

    @GetMapping("/withdraw")
    public void withdrawMoney(
            @RequestParam(value = "accountId") Long accountId,
            @RequestParam(value = "amount") Double amount) {
        accountService.withdrawMoney(accountId, amount);
    }

    @GetMapping("/topup")
    public void topUpAccount(
            @RequestParam(value = "accountId") Long accountId,
            @RequestParam(value = "amount") Double amount) {
        accountService.topUpAccount(accountId, amount);
    }

}
