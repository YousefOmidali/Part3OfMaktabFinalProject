package com.maktab.Part3MaktabFinalProject.controller;

import com.maktab.Part3MaktabFinalProject.entity.*;
import com.maktab.Part3MaktabFinalProject.services.CustomerService;
import com.maktab.Part3MaktabFinalProject.services.ExpertsService;
import com.maktab.Part3MaktabFinalProject.services.OrderService;
import com.maktab.Part3MaktabFinalProject.services.SubServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"order", "Order"})
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final SubServiceService subServiceService;
    private final ExpertsService expertsService;

    public OrderController(OrderService orderService, CustomerService customerService, SubServiceService subServiceService, ExpertsService expertsService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.subServiceService = subServiceService;
        this.expertsService = expertsService;
    }

    @GetMapping({"saveOrder", "saveorder", "updateorder", "updateOrder"})
    public String saveOrUpdate(Model model) {
        model.addAttribute("order", new Order());
        return "orderRegisterPage";
    }

    @PostMapping({"saveOrder", "saveorder", "updateorder", "updateOrder"})
    public String saveOrUpdate(@ModelAttribute("order") Order order) {
        var customer = customerService.findById(order.getCustomer().getId());
        var subService = subServiceService.findById(order.getSubService().getId());
        orderService.saveOrUpdate(new Order(customer, subService, String.valueOf(LocalDateTime.now())
                , order.getSuggestedPrice(), order.getWorkDescription(), order.getWorkDate()
                , order.getAddress(), Boolean.FALSE, OrderStatus.WaitingForExpertsSuggestion));
        return "success";
    }

    @GetMapping({"acceptOrder", "AcceptOrder", "acceptorder", "Acceptorder"})
    public String acceptOrder(Model model) {
        model.addAttribute("order", new Order());
        return "acceptOrder";
    }

    @PostMapping({"acceptOrder", "AcceptOrder", "acceptorder", "Acceptorder"})
    public String acceptOrder(@ModelAttribute("order") Order order) {
        var mainOrder = orderService.findById(order.getId());
        mainOrder.setAccepted(Boolean.TRUE);
        mainOrder.setOrderStatus(OrderStatus.ThisOrderIsChooseByAnExpert);
        orderService.saveOrUpdate(mainOrder);
        return "success";
    }

    @GetMapping({"allOrdersAnExpertCanSee", "allordersanexpertcansee"})
    public String allOrdersAnExpertCanSee(Model model) {
        model.addAttribute("expert", new Experts());
        return "allOrdersAnExpertCanSee";
    }


    @PostMapping({"allOrdersAnExpertCanSee", "allordersanexpertcansee"})
    public String allOrdersAnExpertCanSee(@ModelAttribute Experts experts, Model model) {
        var expert = expertsService.findById(experts.getId());
        List<Order> ordersOfAnExpert = new ArrayList<>();
        for (SubService s : expert.getSubService()) {
            if (orderService.allOrdersOfASubService(s).isEmpty()) {
                System.out.println("is empty");
            } else {
                for (Order order : orderService.allOrdersOfASubService(s)) {
                    if (order.getOrderStatus().equals(OrderStatus.WaitingForExpertsSuggestion))
                        ordersOfAnExpert.add(order);
                }
            }
        }
        model.addAttribute("orders", ordersOfAnExpert);
        return "ordersOfAnExpert";
    }

    @GetMapping({"customerOrder", "CustomerOrder", "customerorder", "Customerorder"})
    public String allOrdersOfACustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "allOrdersOfCustomer";
    }

    @PostMapping({"customerOrder", "CustomerOrder", "customerorder", "Customerorder"})
    public String allOrdersOfACustomer(@ModelAttribute Customer customer, Model model) {
        var allOrdersOfACustomer = orderService.allOrdersOfACustomer(customer.getId());
        model.addAttribute("orders", allOrdersOfACustomer);
        return "allOrdersOfCustomerResult";
    }
}
