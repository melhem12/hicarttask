package com.melhem.hicarttask.repositories;

import com.melhem.hicarttask.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepos extends JpaRepository<Order,Long> {
}
