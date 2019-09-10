package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    Customer c;

    @Before
    public void setUp() throws Exception {
        c = new Customer();
        c.setFirstName("Jim");
        c.setLastName("Jones");
        c.setAddress("111 Main St");
        c.setCity("Dallas");
        c.setState("TX");
        c.setZip("90210");
        c.setPhoneNumber("111-222-3333");
        repository.save(c);
    }

    @Test
    public void addCustomer() throws Exception {
        service.addCustomer(c);
        List<Customer> list = service.getAllCustomers();
        assertTrue(list.size() > 0);
    }

    @Test
    public void getCustomersByState() throws Exception {
        List<Customer> list = service.getCustomersByState("TX");
        assertTrue(list.size() > 0);
        assertEquals("Jim", list.get(0).getFirstName());
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> list = service.getAllCustomers();
        assertTrue(list.size() > 0);
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer c1 = service.getCustomerById(c.getCustomerId());
        assertEquals("Jones", c1.getLastName());
    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
        c.setPhoneNumber("777-888-9999");
        Customer c1 = service.updateCustomerPhoneNumber(c);
        assertEquals("777-888-9999", c1.getPhoneNumber());
    }
}
