package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.*;
import com.maktab.Part3MaktabFinalProject.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping({"Admin", "admin"})
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminRegisterPage";
    }

    @PostMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(@ModelAttribute("admin") Admin admin) {
        adminService.saveOrUpdate(admin);
        return "success";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLoginPage";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("expert") Admin admin) {
        var loginAdmin = adminService.login(admin.getUsername(), admin.getPassword());
        if (loginAdmin != null) {
            return "adminMenu";
        }
        return "wrongPassword";
    }

    @GetMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminSearchPage";
    }

    @PostMapping({"search", "gridSearch", "gridsearch"})
    public String gridSearch(@ModelAttribute("admin") Admin admin, Model model) {
        var list = adminService.gridSearch(admin.getFirstname(), admin.getLastname()
                , admin.getEmail(), admin.getUsername());;
        model.addAttribute("adminList", list);
        return "adminSearchResultPage";
    }
}
