package com.galvanize.customer.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    @Autowired
    CustomerService service;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addCustomer() throws Exception {
    }

    @Test
    public void getCustomersByLastName() throws Exception {
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
