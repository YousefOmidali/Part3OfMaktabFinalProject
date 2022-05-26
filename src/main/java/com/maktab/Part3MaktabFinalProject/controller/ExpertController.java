package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.Customer;
import com.maktab.Part3MaktabFinalProject.entity.Experts;
import com.maktab.Part3MaktabFinalProject.entity.UserStatus;
import com.maktab.Part3MaktabFinalProject.entity.Wallet;
import com.maktab.Part3MaktabFinalProject.services.ExpertsService;
import com.maktab.Part3MaktabFinalProject.services.WalletService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Controller
@RequestMapping({"Expert", "expert"})
public class ExpertController {
    private final ExpertsService expertsService;
    private final WalletService walletService;

    public ExpertController(ExpertsService expertsService, WalletService walletService) {
        this.expertsService = expertsService;
        this.walletService = walletService;
    }

    @GetMapping("expertRegister")
    public String saveOrUpdate(Model model) {
        model.addAttribute("expert", new Experts());
        return "expertRegisterPage";
    }

    @PostMapping("expertRegister")
    public String saveOrUpdate(@ModelAttribute("expert") Experts experts) {
        walletService.saveOrUpdate(experts.getWallet());
        experts.setImage(BlobProxy.generateProxy(expertsService.getImage(experts.getImageLink())));
        experts.setImageLink("Image is connected");
        experts.setUserStatus(UserStatus.NEW);
        experts.setLikes(0L);
        experts.setSignUpTime(String.valueOf(LocalDateTime.now()));
        expertsService.saveOrUpdate(experts);
        return "success";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("expert", new Experts());
        return "expertLoginPage";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("expert") Experts expert) {
        var loginExpert = expertsService.login(expert.getUsername(), expert.getPassword());
        if (loginExpert != null) {
            return "expertMenu";
        }
        return "wrongPassword";
    }

    @GetMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(Model model) {
        model.addAttribute("expert", new Experts());
        return "expertSearchPage";
    }

    @PostMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(@ModelAttribute("expert") Experts experts, Model model) {
        var list = expertsService.gridSearch(experts.getFirstname(), experts.getLastname()
                , experts.getEmail(), experts.getUsername());
        model.addAttribute("expertList", list);
        return "expertSearchResultPage";
    }
}

