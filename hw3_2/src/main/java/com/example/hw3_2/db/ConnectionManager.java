package com.example.hw3_2.db;

import com.example.hw3_2.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager instance;
    private static SessionFactory sessionFactory;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/db");
            properties.put(Environment.USER, "karin");
            properties.put(Environment.PASS, "241002");
            properties.setProperty("hibernate.allow_update_outside_transaction",  "true");
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Rate.class);
            configuration.addAnnotatedClass(Room.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Guest.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Banned.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return instance;
    }

    private ConnectionManager() {

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
