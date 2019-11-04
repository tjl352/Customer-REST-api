package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerServiceTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private Customer c;

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
        customerRepository.save(c);
    }

    @Test
    public void exists(){
        assertNotNull(customerService);
    }

    @Test
    public void getOneCustomerById(){
        assertEquals("Jim", customerService.getOneCustomerById(c.getCustomerId()).getFirstName());
    }

    @Test
    public void getAllCustomers(){
        for(Customer c: customerService.getAllCustomers()){
            assertEquals("Jim", customerService.getAllCustomers().get(0).getFirstName());
        }
    }

   @Test
   public void addCustomer(){
       Customer c1 = new Customer();
       c1.setFirstName("Tim");
       c1.setLastName("Jones");
       c1.setAddress("111 Main St");
       c1.setCity("Dallas");
       c1.setState("TX");
       c1.setZip("90210");
       c1.setPhoneNumber("111-222-3333");
       customerRepository.save(c1);
        customerService.addCustomer(c1);
        assertEquals("Tim", customerService.getOneCustomerById(c1.getCustomerId()).getFirstName());
   }

   @Test
    public void updateCustomer(){
       Customer c1 = new Customer();
       c1.setCustomerId(c.getCustomerId());
       c1.setFirstName("Tim");
       c1.setLastName("Jones");
       c1.setAddress("111 Main St");
       c1.setCity("Dallas");
       c1.setState("TX");
       c1.setZip("90210");
       c1.setPhoneNumber("111-222-3333");
        customerService.updateCustomer(c1);
       assertEquals("Tim", customerService.getOneCustomerById(c.getCustomerId()).getFirstName());
   }
}
