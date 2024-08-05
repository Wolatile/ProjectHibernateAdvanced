package com.example.dao;

import com.example.domain.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends AbstractHibernateDAO<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
