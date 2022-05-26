package com.maktab.Part3MaktabFinalProject.repository;

import com.maktab.Part3MaktabFinalProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByUsernameAndPassword(String username, String password);

    List<Customer> findCustomersByFirstnameOrLastnameOrEmailOrUsername(
            String firstname
            , String lastname
            , String email
            , String username);
}
