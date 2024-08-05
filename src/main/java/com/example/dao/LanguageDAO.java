package com.example.dao;

import com.example.domain.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends AbstractHibernateDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
