package com.drake7v.accounts.repository;

import com.drake7v.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    //derived name method prepared on the name after findBy
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
