package com.melhem.hicarttask.repositories;

import com.melhem.hicarttask.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepos  extends JpaRepository<Customer,Long> {

    Optional<Customer> findByCustomerName(String customerName);
}
