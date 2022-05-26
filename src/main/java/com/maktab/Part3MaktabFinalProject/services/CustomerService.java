package com.maktab.Part3MaktabFinalProject.services;

import com.maktab.Part3MaktabFinalProject.entity.Customer;
import com.maktab.Part3MaktabFinalProject.repository.CustomerRepository;
import com.maktab.Part3MaktabFinalProject.services.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements BaseService<Customer, Long> {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer findById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer login(String username, String password) {
        return customerRepository.findCustomerByUsernameAndPassword(username, password);
    }

    @Transactional
    public List<Customer> gridSearch(String firstName,
                                     String lastName,
                                     String email,
                                     String username) {
        return customerRepository.findCustomersByFirstnameOrLastnameOrEmailOrUsername(firstName, lastName, email, username);
    }
}
