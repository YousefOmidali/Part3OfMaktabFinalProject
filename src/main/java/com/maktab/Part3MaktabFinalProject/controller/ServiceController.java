package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.Service;
import com.maktab.Part3MaktabFinalProject.services.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping({"Service", "service"})
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @GetMapping("serviceRegister")
    public String saveOrUpdate(Model model) {
        model.addAttribute("service", new Service());
        return "serviceRegisterPage";
    }

    @PostMapping("serviceRegister")
    public String saveOrUpdate(@ModelAttribute("service") Service service) {
        System.out.println(service);
        serviceService.saveOrUpdate(service);
        return "success";
    }


    @GetMapping("findAll")
    public String findAll(Model model) {
        model.addAttribute("serviceList", serviceService.findAll());
        return "findAllService";
    }
}
