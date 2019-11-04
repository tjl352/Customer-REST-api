package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CustomerRestControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerService customerService;

    Customer c1;

    @Before
    public void setup(){
        c1 = new Customer();
        c1.setFirstName("Tim");
        c1.setLastName("Jones");
        c1.setAddress("111 Main St");
        c1.setCity("Dallas");
        c1.setState("TX");
        c1.setZip("90210");
        customerService.addCustomer(c1);
    }

    @Test
    public void addCustomer() throws Exception {
        mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"));
    }

    @Test
    public void getCustomer() throws Exception {
        mvc.perform(get("/customer/" + c1.getCustomerId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Tim"));
    }

    @Test
    public void updateCustomer() throws Exception {
        mvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON).content(getJsonUpdate()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"));
    }

    @Test
    public void deleteCustomer() throws Exception{
        mvc.perform(delete("/customer").contentType(MediaType.APPLICATION_JSON).content(getJsonUpdate())).andExpect(status().isOk());
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

    private String getJsonUpdate(){
        StringBuffer buff = new StringBuffer();
        buff.append("{\n");
        buff.append("\"customerId\": \""+ c1.getCustomerId() +"\",\n");
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
