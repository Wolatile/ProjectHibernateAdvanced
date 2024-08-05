package com.example.dao;

import com.example.domain.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends AbstractHibernateDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
