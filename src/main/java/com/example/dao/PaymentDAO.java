package com.example.dao;

import com.example.domain.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends AbstractHibernateDAO<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
