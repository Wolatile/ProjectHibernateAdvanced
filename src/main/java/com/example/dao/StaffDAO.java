package com.example.dao;

import com.example.domain.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends AbstractHibernateDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
