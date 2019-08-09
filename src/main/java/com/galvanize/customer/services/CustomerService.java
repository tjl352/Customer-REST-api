package com.galvanize.customer.services;

import com.galvanize.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service @Component
public class CustomerService {

    @Autowired
    CustomerRepository repository;



}
