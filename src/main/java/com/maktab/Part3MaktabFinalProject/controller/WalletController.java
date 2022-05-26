package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.services.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"wallet","Wallet"})
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping({"charge","Charge"})
    @ResponseBody
    public String takeASubService(@RequestBody Long walletId, Long amount) {
      var wallet = walletService.findById(walletId);
      var walletCurrentAmount = wallet.getAmount();
      wallet.setAmount((walletCurrentAmount+amount));
      walletService.saveOrUpdate(wallet);
        return "";
    }
}
