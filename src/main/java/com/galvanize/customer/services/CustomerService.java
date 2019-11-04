package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getOneCustomerById(long customerId) {
        return customerRepository.getOne(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Customer c) {
        customerRepository.delete(c);
    }

    public Customer addCustomer(Customer c) {
        return customerRepository.save(c);
    }

    public Customer updateCustomer(Customer c) {
        return customerRepository.save(c);
    }
}
