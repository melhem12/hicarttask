package com.melhem.hicarttask.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DB {
    @PersistenceContext
    public EntityManager em;

    @Autowired
    public CustomerRepos customerRepos;
    @Autowired
    public OrderRepos orderRepos;

}
