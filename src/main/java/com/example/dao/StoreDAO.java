package com.example.dao;

import com.example.domain.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends AbstractHibernateDAO<Store> {
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
