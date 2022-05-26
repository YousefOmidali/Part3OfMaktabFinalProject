package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.UI.ExpertIdAndSubServiceId;
import com.maktab.Part3MaktabFinalProject.entity.SubService;
import com.maktab.Part3MaktabFinalProject.services.ExpertsService;
import com.maktab.Part3MaktabFinalProject.services.ServiceService;
import com.maktab.Part3MaktabFinalProject.services.SubServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"SubService", "subservice", "Subservice", "subService"})
public class SubServiceController {
    private final SubServiceService subServiceService;
    private final ServiceService serviceService;
    private final ExpertsService expertsService;

    public SubServiceController(SubServiceService subServiceService, ServiceService serviceService, ExpertsService expertsService) {
        this.subServiceService = subServiceService;
        this.serviceService = serviceService;
        this.expertsService = expertsService;
    }


    @GetMapping("subServiceRegister")
    public String saveOrUpdate(Model model) {
        model.addAttribute("subService", new SubService());
        return "subServiceRegisterPage";
    }

    @PostMapping("subServiceRegister")
    public String saveOrUpdate(@ModelAttribute("subService") SubService subService) {
        var service = serviceService.findById(subService.getService().getId());
        subService.setService(service);
        subServiceService.saveOrUpdate(subService);
        return "success";
    }

    @GetMapping("findAll")
    public String findAll(Model model) {
        model.addAttribute("subServiceList", subServiceService.findAll());
        return "findAllSubServices";
    }

    @GetMapping("takeASubService")
    public String takeASubService(Model model) {
        model.addAttribute("expertIdAndSubServiceId", new ExpertIdAndSubServiceId());
        return "takeASubServiceForAnExpertPage";
    }

    @PostMapping("takeASubService")
    public String takeASubService(@ModelAttribute("subService") ExpertIdAndSubServiceId expertIdAndSubServiceId) {
        var expert = expertsService.findById(expertIdAndSubServiceId.getExpertId());
        var subService = subServiceService.findById(expertIdAndSubServiceId.getSubServiceId());
        expert.getSubService().add(subService);
        expertsService.saveOrUpdate(expert);

        subService.getExperts().add(expert);
        subServiceService.saveOrUpdate(subService);
        return "success";
    }

}
