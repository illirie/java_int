package com.example.hw3_2.service.implemented;

import com.example.hw3_2.db.ConnectionManager;
import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Banned;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.service.BannedService;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class BannedServiceImpl implements BannedService {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();
    @Override
    public List<Banned> findAll() {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Banned> cq = cb.createQuery(Banned.class);
        Root<Banned> rootEntry = cq.from(Banned.class);
        CriteriaQuery<Banned> all = cq.select(rootEntry);
        TypedQuery<Banned> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Banned findById(Integer id) throws NotFoundException {
        Session session = connectionManager.getSessionFactory().openSession();
        Banned banned = session.find(Banned.class, id);
        session.close();
        if (banned == null) {
            throw new NotFoundException("Banned not found");
        }
        return banned;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = connectionManager.getSessionFactory().openSession();
        Banned banned = session.find(Banned.class, id);
        session.delete(banned);
        session.flush();
        session.close();
    }

    @Override
    public void update(Banned banned) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.update(banned);
        session.flush();
        session.close();
    }

    @Override
    public void save(Banned banned) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.persist(banned);
        session.flush();
        session.close();
    }
}
