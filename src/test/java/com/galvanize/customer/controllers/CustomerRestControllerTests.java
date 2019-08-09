package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerRestControllerTests {

    private String BASE_URI = "/";

    @Autowired
    MockMvc mvc;

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    List<Customer> customers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Customer customer = null;
        for (int i = 0; i < 10; i++) {
            customer = new Customer();

            customer.setFirstName("FirstName" + i);
            customer.setLastName("LastName" + i);
            customer.setAddress((i * 67) + " Any Street");
            customer.setCity("AnyCity " + i);
            customer.setState(i % 2 == 0 ? "NY" : "CA");
            customer.setZip(i + "000" + i);
            customer.setPhoneNumber("111-111-111" + i);
            customer.setDateJoined(new Date());

            customers.add(customer);
        }

        repository.saveAll(customers);
    }

    @Test
    public void addCustomer() throws Exception {
        MockHttpServletRequestBuilder request = post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson());
         mvc.perform(request)
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.customerId").exists());
    }

    @Test
    public void getCustomersByState() throws Exception {
        mvc.perform(get("/?state=NY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].state", is("NY")))
                .andExpect(jsonPath("$[3].state", is("NY")));
    }

    @Test
    public void getAllCustomers() throws Exception {
        mvc.perform(get("/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer customer = customers.get(7);
        mvc.perform(get("/"+customer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(customer.getCustomerId().intValue())));
    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
        Customer customer = customers.get(4);
        String newPhone = "999.999.9999";

        String json = String.format("{ \"customerId\":\"%s\",\"phoneNumber\":\"%s\" }", customer.getCustomerId(), newPhone);

        MockHttpServletRequestBuilder request = post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber", is(newPhone)));

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
