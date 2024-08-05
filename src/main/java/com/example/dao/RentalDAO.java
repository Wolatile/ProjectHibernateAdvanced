package com.example.dao;

import com.example.domain.Inventory;
import com.example.domain.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractHibernateDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getByInventory(Inventory inventory) {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.inventory = :name", Rental.class);
        query.setParameter("name", inventory);
        return query.getSingleResult();
    }
}
