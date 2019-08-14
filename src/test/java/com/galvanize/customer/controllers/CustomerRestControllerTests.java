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

    private String getJson(){
        StringBuffer buff = new StringBuffer();
        buff.append("{\n");

        buff.append("\"firstName\": \"theFirstName\",\n");
        buff.append("\"lastName\": \"theLastName\",\n");
        buff.append("\"address\": \"theAddress\",\n");
        buff.append("\"city\": \"theCity\",\n");
        buff.append("\"state\": \"theState\",\n");
        buff.append("\"zip\": \"theZip\",\n");
        buff.append("\"phoneNumber\": \"520-555-1212\",\n");
        buff.append("\"joinDate\": \"08/09/2019\"\n");

        buff.append("}");

        return buff.toString();
    }
}
