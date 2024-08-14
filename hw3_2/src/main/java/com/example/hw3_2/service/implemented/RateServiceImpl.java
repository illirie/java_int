package com.example.hw3_2.service.implemented;

import com.example.hw3_2.db.ConnectionManager;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.RateService;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import com.example.hw3_2.exception.NotFoundException;
import org.hibernate.Transaction;

public class RateServiceImpl implements RateService {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public Rate findById(Integer id) throws NotFoundException {
        Session session = connectionManager.getSessionFactory().openSession();
        Rate rate = session.get(Rate.class, id);
        session.close();
        if (rate == null) {
            throw new NotFoundException("Rate not found by id");
        }
        return rate;
    }

    @Override
    public List<Rate> findAll() {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Rate> cq = cb.createQuery(Rate.class);
        Root<Rate> rootEntry = cq.from(Rate.class);
        CriteriaQuery<Rate> all = cq.select(rootEntry);
        TypedQuery<Rate> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Rate rate) {
        Session session = connectionManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(rate);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = connectionManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Rate rate = session.get(Rate.class, id);
        session.delete(rate);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Rate rate) {
        Session session = connectionManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(rate);
        tx1.commit();
        session.close();
    }
}
