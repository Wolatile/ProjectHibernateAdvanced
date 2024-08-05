package com.example.dao;

import com.example.domain.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends AbstractHibernateDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
