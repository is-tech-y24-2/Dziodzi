package ru.itmo.kotiki.tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
public class HibernateUtilUpdateTable {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(new File("C:\\Users\\Gregory\\Desktop\\Dziodzi\\kotiki-java\\dao\\src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            initSessionFactory();
        }
        return sessionFactory;
    }
    public static void close() {
        getSessionFactory().close();
    }
}