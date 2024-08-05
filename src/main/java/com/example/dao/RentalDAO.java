package com.example.dao;

import com.example.domain.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends AbstractHibernateDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
