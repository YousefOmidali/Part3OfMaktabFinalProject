package com.maktab.Part3MaktabFinalProject.controller;


import com.maktab.Part3MaktabFinalProject.entity.UI.ChargeWallet;
import com.maktab.Part3MaktabFinalProject.services.CustomerService;
import com.maktab.Part3MaktabFinalProject.services.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"wallet", "Wallet"})
public class WalletController {
    private final WalletService walletService;
    private final CustomerService customerService;

    public WalletController(WalletService walletService, CustomerService customerService) {
        this.walletService = walletService;
        this.customerService = customerService;
    }

    @GetMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(Model model) {
        model.addAttribute("chargeWallet", new ChargeWallet());
        return "chargeWallet";
    }

    @PostMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(@ModelAttribute("chargeWallet") ChargeWallet chargeWallet) {
        var mainCustomer = customerService.findById(chargeWallet.getCustomerId());
        var wallet = mainCustomer.getWallet();
        var currentAmount = wallet.getAmount();
        currentAmount += chargeWallet.getAmount();
        wallet.setAmount(currentAmount);
        walletService.saveOrUpdate(wallet);
        return "success";
    }
}
