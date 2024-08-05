package com.example.dao;

import com.example.domain.Feature;
import org.hibernate.SessionFactory;

public class FeatureDAO extends AbstractHibernateDAO<Feature> {
    public FeatureDAO(SessionFactory sessionFactory) {
        super(Feature.class, sessionFactory);
    }
}
