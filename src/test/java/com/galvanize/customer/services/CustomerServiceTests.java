package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTests {

    CustomerService service;

    @Mock
    CustomerRepository repository;

    List<Customer> list;
    Customer c;

    @Before
    public void setUp(){
        service = new CustomerService(repository);

        c = new Customer(1l,"Bob", "TX");

        list = new ArrayList();
        list.add(new Customer(2l, "Pat", "TX"));
        list.add(new Customer(3l, "Jim", "TX"));
        for (int i = 0; i < list.size(); i++) {
            service.addCustomer(list.get(i));
        }
    }

    @Test
    public void addCustomerAndGetByCustomerId(){
        service.addCustomer(c);
        when(repository.findById(1l)).thenReturn(java.util.Optional.ofNullable(c));
        Customer c1 = service.getCustomerById(1l);
        assertNotNull(c1);
    }

    @Test
    public void getCustomersByState(){
        when(service.getCustomersByState("TX")).thenReturn(list);
        List<Customer> newList = service.getCustomersByState("TX");
        assertTrue(newList.size() > 0);
        assertEquals("Pat", newList.get(0).getFirstName());
    }

    @Test
    public void getAllCustomers(){
        when(service.getAllCustomers()).thenReturn(list);
        List<Customer> newList = service.getAllCustomers();
        assertTrue(newList.size() > 0);
    }
}
