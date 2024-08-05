package com.example.dao;

import com.example.domain.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends AbstractHibernateDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
