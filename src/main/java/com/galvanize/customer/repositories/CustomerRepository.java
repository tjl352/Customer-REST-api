package com.galvanize.customer.repositories;

import com.galvanize.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    ArrayList<Customer> getCustomersByState(String state);

}
