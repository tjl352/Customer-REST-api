package com.galvanize.customer.controllers;

import com.galvanize.customer.repositories.CustomerRepository;
import com.galvanize.customer.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerRestControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addCustomer() throws Exception {
    }

    @Test
    public void getCustomersByState() throws Exception {
    }

    @Test
    public void getAllCustomers() throws Exception {
    }

    @Test
    public void getCustomerById() throws Exception {
    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
    }
}
