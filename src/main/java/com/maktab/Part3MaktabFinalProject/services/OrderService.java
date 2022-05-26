package com.maktab.Part3MaktabFinalProject.services;


import com.maktab.Part3MaktabFinalProject.entity.Order;
import com.maktab.Part3MaktabFinalProject.entity.SubService;
import com.maktab.Part3MaktabFinalProject.repository.OrderRepository;
import com.maktab.Part3MaktabFinalProject.services.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements BaseService<Order, Long> {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public List<Order> allOrdersOfASubService(SubService subService) {
        return orderRepository.findAllBySubServiceEquals(subService);
    }

    @Transactional
    public List<Order> allOrdersOfACustomer(Long id) {
        return orderRepository.findAllByCustomer_Id(id);
    }
}
