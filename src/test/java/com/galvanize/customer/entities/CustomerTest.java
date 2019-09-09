package com.galvanize.customer.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

    Customer c = new Customer();

    @Test
    public void customerIdTest(){
        c.setCustomerId(12L);
        assertEquals(12L, c.getCustomerId());
    }

    @Test
    public void firstNameTest(){
        c.setFirstName("Jim");
        assertEquals("Jim", c.getFirstName());
    }

    @Test
    public void lastNameTest(){
        c.setLastName("Jones");
        assertEquals("Jones", c.getLastName());
    }

    @Test
    public void addressTest(){
        c.setAddress("111 Main St");
        assertEquals("111 Main St", c.getAddress());
    }

    @Test
    public void cityTest(){
        c.setCity("Dallas");
        assertEquals("Dallas", c.getCity());
    }

    @Test
    public void stateTest(){
        c.setState("TX");
        assertEquals("TX", c.getState());
    }

    @Test
    public void zipTest(){
        c.setZip("90210");
        assertEquals("90210", c.getZip());
    }

    @Test
    public void phoneTest(){
        c.setPhoneNumber("111-222-3333");
        assertEquals("111-222-3333", c.getPhoneNumber());
    }
}
