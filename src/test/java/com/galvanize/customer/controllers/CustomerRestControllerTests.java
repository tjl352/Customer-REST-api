package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import com.galvanize.customer.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerRestController.class)
@RunWith(SpringRunner.class)
public class CustomerRestControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService service;

    Customer c;
    List<Customer> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        c = new Customer();
        c.setCustomerId(10l);
        c.setFirstName("Jim");
        c.setLastName("Jones");
        c.setAddress("111 Main St");
        c.setCity("Dallas");
        c.setState("TX");
        c.setZip("90210");
        c.setPhoneNumber("111-222-3333");
        for (int i = 0; i < 5; i++) {
            list.add(new Customer(Long.valueOf(i), "Bob", "TX"));
        }
    }

    @Test
    public void getAllCustomers() throws Exception {
        when(service.getAllCustomers()).thenReturn(list);
        mvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Bob"));
    }

    @Test
    public void addCustomer() throws Exception {
        when(service.addCustomer(any())).thenReturn(c);
        MockHttpServletRequestBuilder postRequest = post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
        mvc.perform(postRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomersByState() throws Exception {
        when(service.getCustomersByState("TX")).thenReturn(list);
        mvc.perform(get("/customers?state=TX"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerById() throws Exception {
        when(service.getCustomerById(10l)).thenReturn(c);
        mvc.perform(get("/customer/" + c.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jim"));
    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
        MockHttpServletRequestBuilder putRequest = put("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
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
}
