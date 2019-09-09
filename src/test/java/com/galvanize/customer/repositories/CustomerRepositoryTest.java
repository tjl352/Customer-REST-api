package com.galvanize.customer.repositories;

import com.galvanize.customer.entities.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

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
    public void getCustomersByStateTest(){
        repository.save(c);
        List<Customer> list = repository.getCustomersByState("TX");
        assertNotNull(list);
        assertTrue(list.size() > 0);
        assertEquals("Jim", list.get(0).getFirstName());
    }
}
