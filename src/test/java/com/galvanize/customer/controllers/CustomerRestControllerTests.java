package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import com.galvanize.customer.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    Customer c;

    @Before
    public void setUp() throws Exception {
        c = new Customer();
        c.setCustomerId(312L);
        c.setFirstName("Jim");
        c.setLastName("Jones");
        c.setAddress("111 Main St");
        c.setCity("Dallas");
        c.setState("TX");
        c.setZip("90210");
        c.setPhoneNumber("111-222-3333");
    }

    @Test
    public void addCustomer() throws Exception {
        MockHttpServletRequestBuilder postRequest = post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
        mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"));
    }

    @Test
    public void getCustomersByState() throws Exception {
        addCustomer();

        mvc.perform(get("/all/DC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Jones"));
    }

    @Test
    public void getAllCustomers() throws Exception {
        mvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jim"));
    }

    @Test
    public void getCustomerById() throws Exception {
        mvc.perform(get("/get/312"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jim"));
    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
        MockHttpServletRequestBuilder putRequest = put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson());
        mvc.perform(putRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("520-555-1212"));
    }

    private String getJson(){
        StringBuffer buff = new StringBuffer();
        buff.append("{\n");

        buff.append("\"firstName\": \"Bob\",\n");
        buff.append("\"lastName\": \"Jones\",\n");
        buff.append("\"address\": \"111 F Street\",\n");
        buff.append("\"city\": \"Washington\",\n");
        buff.append("\"state\": \"DC\",\n");
        buff.append("\"zip\": \"20001\",\n");
        buff.append("\"phoneNumber\": \"520-555-1212\",\n");
        buff.append("\"joinDate\": \"08/09/2019\"\n");

        buff.append("}");

        return buff.toString();
    }

    private String updateJson(){
        StringBuffer buff = new StringBuffer();
        buff.append("{\n");

        buff.append("\"firstName\": \"Jim\",\n");
        buff.append("\"lastName\": \"Jones\",\n");
        buff.append("\"address\": \"111 Main St\",\n");
        buff.append("\"city\": \"Dallas\",\n");
        buff.append("\"state\": \"TX\",\n");
        buff.append("\"zip\": \"90210\",\n");
        buff.append("\"phoneNumber\": \"520-555-1212\",\n");
        buff.append("\"joinDate\": \"08/09/2019\"\n");

        buff.append("}");

        return buff.toString();
    }
}
