package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.Customer;
import com.maktab.Part3MaktabFinalProject.entity.UserStatus;
import com.maktab.Part3MaktabFinalProject.services.CustomerService;
import com.maktab.Part3MaktabFinalProject.services.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@Controller
@RequestMapping({"Customer", "customer"})
public class CustomerController {
    private final CustomerService customerService;
    private final WalletService walletService;

    public CustomerController(CustomerService customerService, WalletService walletService) {
        this.customerService = customerService;
        this.walletService = walletService;
    }

    @GetMapping("customerRegister")
    public String saveOrUpdate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerRegisterPage";
    }

    @PostMapping("customerRegister")
    public String saveOrUpdate(@ModelAttribute("customer") Customer customer) {
        walletService.saveOrUpdate(customer.getWallet());
        customer.setUserStatus(UserStatus.NEW);
        customer.setSignUpTime(String.valueOf(LocalDateTime.now()));
        customerService.saveOrUpdate(customer);
        return "success";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerLoginPage";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("customer") Customer customer) {
        var loginCustomer = customerService.login(customer.getUsername(),customer.getPassword());
       if (loginCustomer != null){
           return "customerMenu";
       }
        return "wrongPassword";
    }


    @GetMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerSearchPage";
    }

    @PostMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(@ModelAttribute("expert") Customer customer, Model model) {
        var list = customerService.gridSearch(customer.getFirstname(), customer.getLastname()
                , customer.getEmail(), customer.getUsername());
        System.out.println(list.isEmpty());
        model.addAttribute("customerList", list);
        return "customerSearchResultPage";
    }

}
