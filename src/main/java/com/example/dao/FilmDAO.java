package com.example.dao;

import com.example.domain.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends AbstractHibernateDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
