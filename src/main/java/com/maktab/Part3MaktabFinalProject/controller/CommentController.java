package com.maktab.Part3MaktabFinalProject.controller;


import com.maktab.Part3MaktabFinalProject.entity.Comment;
import com.maktab.Part3MaktabFinalProject.entity.Customer;
import com.maktab.Part3MaktabFinalProject.entity.OrderStatus;
import com.maktab.Part3MaktabFinalProject.services.CommentService;
import com.maktab.Part3MaktabFinalProject.services.CustomerService;
import com.maktab.Part3MaktabFinalProject.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping({"comment", "Comment"})
public class CommentController {
    private final CommentService commentService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public CommentController(CommentService commentService, CustomerService customerService, OrderService orderService) {
        this.commentService = commentService;
        this.customerService = customerService;
        this.orderService = orderService;
    }


    @GetMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(Model model) {
        model.addAttribute("comment", new Comment());
        return "commentRegister";
    }

    @PostMapping({"Save", "save", "Update", "update"})
    public String saveOrUpdate(@ModelAttribute("comment") Comment comment) {
        var order = orderService.findById(comment.getOrder().getId());
        comment.setOrder(order);
        var customer = customerService.findById(comment.getCustomer().getId());
        comment.setCustomer(customer);
        if (order.getOrderStatus().equals(OrderStatus.ThisOrderIsChooseByAnExpert)) {
            commentService.saveOrUpdate(comment);
            return "success";
        } else
            return "commentError";
    }


    @GetMapping({"findAll", "findall", "FindAll", "findALL"})
    public String findAll(Model model) {
        model.addAttribute("customer", new Customer());
        return "commentsOfACustomer";
    }
    @PostMapping({"findAll", "findall", "FindAll", "findALL"})
    public String findAll(@ModelAttribute Customer customer, Model model) {
        var commentsOfACustomer = commentService.commentsOfACustomer(customer.getId());
        model.addAttribute("comments", commentsOfACustomer);
        return "commentsOfACustomerResult";
    }
}
