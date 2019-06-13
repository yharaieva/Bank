package com.haraieva.bank.controller;

import com.haraieva.bank.dto.AccountDto;
import com.haraieva.bank.dto.TransferRequest;
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
                                        @RequestParam(value = "clientId", required = false) Long clientId) {
        model.addAttribute("accounts", clientId != null
                ? accountService.findAllByClientId(clientId)
                : accountService.findAllAccounts());
        return "accounts";
    }

    @PostMapping("/addAccount")
    public String addAccount(@ModelAttribute("account") AccountDto accountDto) {
        clientService.addAccount(accountDto);
        return "index";
    }

    @GetMapping("/addAccount")
    public String addAccountView(Model model) {
        model.addAttribute("account", new AccountDto());
        return "addAccount";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@ModelAttribute("transfer") TransferRequest request) {
        accountService.transfer(request);
        return "index";
    }

    @GetMapping("/transfer")
    public String makeTransferView(Model model) {
        model.addAttribute("transfer", new TransferRequest());
        return "makeTransfer";
    }

    @PostMapping("/withdraw")
    public String withdrawMoneyView(@ModelAttribute("transfer") TransferRequest request) {
        accountService.transfer(request);
        return "index";
    }

    @GetMapping("/withdraw")
    public String withdrawMoneyView(Model model) {
        model.addAttribute("transfer", new TransferRequest());
        return "withdrawMoney";
    }

    @PostMapping("/topup")
    public String topUpAccount(@ModelAttribute("transfer") TransferRequest request) {
        accountService.transfer(request);
        return "index";
    }

    @GetMapping("/topup")
    public String topUpAccount(Model model) {
        model.addAttribute("transfer", new TransferRequest());
        return "topUpMoney";
    }

}
