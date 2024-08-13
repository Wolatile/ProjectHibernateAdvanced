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
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.inventory = :inventoryName", Rental.class);
        query.setParameter("inventoryName", inventory);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public Rental getAnyUnreturnedRental() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
