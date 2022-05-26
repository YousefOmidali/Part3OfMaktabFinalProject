package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.*;
import com.maktab.Part3MaktabFinalProject.services.ExpertsService;
import com.maktab.Part3MaktabFinalProject.services.OrderService;
import com.maktab.Part3MaktabFinalProject.services.SuggestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping({"Suggestion", "suggestion"})
public class SuggestionController {
    private final SuggestionService suggestionService;
    private final OrderService orderService;
    private final ExpertsService expertsService;

    public SuggestionController(SuggestionService suggestionService, OrderService orderService, ExpertsService expertsService) {
        this.suggestionService = suggestionService;
        this.orderService = orderService;
        this.expertsService = expertsService;
    }

    @GetMapping("save")
    public String saveOrUpdate(Model model) {
        model.addAttribute("suggestion", new Suggestion());
        return "suggestionRegisterPage";
    }

    @PostMapping("save")
    public String saveOrUpdate(@ModelAttribute("suggestion") Suggestion suggestion) {
        var order = orderService.findById(suggestion.getOrder().getId());
        order.setOrderStatus(OrderStatus.WaitingForCustomerToChooseASuggestion);
        orderService.saveOrUpdate(order);
        suggestion.setOrder(order);
        var expert = expertsService.findById(suggestion.getExperts().getId());
        suggestion.setExperts(expert);
        suggestionService.saveOrUpdate(suggestion);
        return "success";
    }

    @GetMapping({"ofAnOrder", "OfAnOrder", "ofanorder"})
    public String suggestionsOfAnOrder(Model model) {
        model.addAttribute("order", new Order());
        return "suggestionsOfAnOrder";
    }

    @PostMapping({"ofAnOrder", "OfAnOrder", "ofanorder"})
    public String suggestionsOfAnOrder(@ModelAttribute("order") Order order, Model model) {
        var suggestionList = suggestionService.suggestionsOfAnOrder(order.getId())
                .stream()
                .filter(c -> c.getOrder().getOrderStatus().equals(OrderStatus.WaitingForCustomerToChooseASuggestion));
        model.addAttribute("suggestionList", suggestionList.collect(Collectors.toList()));
        return "suggestionsOfAnOrderResultPage";
    }
}
