package com.maktab.Part3MaktabFinalProject.repository;


import com.maktab.Part3MaktabFinalProject.entity.Order;
import com.maktab.Part3MaktabFinalProject.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllBySubServiceEquals(SubService subService);

    List<Order> findAllByCustomer_Id(Long id);
}
