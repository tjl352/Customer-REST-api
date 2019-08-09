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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    private List<Customer> customers = new ArrayList<>();

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    @Before
    public void setUp() throws Exception {
        Customer customer;
        for (int i = 0; i < 10; i++) {
            customer = new Customer();

            customer.setFirstName("FirstName"+i);
            customer.setLastName("LastName"+i);
            customer.setAddress((i*67)+" Any Street");
            customer.setCity("AnyCity "+i);
            customer.setState(i%2==0 ? "NY" : "CA");
            customer.setZip(i+"000"+i);
            customer.setPhoneNumber("111-111-111"+i);
            customer.setDateJoined(new Date());

            customers.add(customer);
        }

        repository.saveAll(customers);
    }

    @Test
    public void addCustomer() throws Exception {
        Customer c = new Customer();
        c.setFirstName("FirstName");
        c.setLastName("LastName");
        c.setState("AZ");
        c.setPhoneNumber("520-555-1212");

        c = service.addCustomer(c);

        assertNotNull("customer id was null after create", c.getCustomerId());
    }

    @Test
    public void getCustomersByState() throws Exception {
        List<Customer> customers = service.findByState("NY");
        assertNotNull(customers);
        assertTrue("No customers returned", customers.size()>0);
        for(Customer cust : customers){
            assertTrue("Customer "+cust.getCustomerId()+"-"+cust.getLastName()+" was not from NY", cust.getState().equals("NY"));
        }
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> customers = service.getAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.size()>0);
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer actualCustomer = customers.get(5);
        Customer customer = service.getCustomer(actualCustomer.getCustomerId());
        assertNotNull(customer);
        assertEquals(actualCustomer.getFirstName(), customer.getFirstName());
        assertEquals(actualCustomer.getLastName(), customer.getLastName());
        assertEquals(actualCustomer.getAddress(), customer.getAddress());
        assertEquals(actualCustomer.getCity(), customer.getCity());
        assertEquals(actualCustomer.getState(), customer.getState());
        assertEquals(actualCustomer.getZip(), customer.getZip());

    }

    @Test
    public void updateCustomerPhoneNumber() throws Exception {
        String newPhone = "999-999-9999";
        Customer customer = customers.get(3);
        customer.setPhoneNumber(newPhone);
        service.update(customer);

        Customer newCust = service.getCustomer(customer.getCustomerId());
        assertNotNull(newCust);
        assertEquals(newPhone, newCust.getPhoneNumber());
    }
}
