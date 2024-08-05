package com.example.dao;

import com.example.domain.City;
import org.hibernate.SessionFactory;

public class CityDAO extends AbstractHibernateDAO <City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
