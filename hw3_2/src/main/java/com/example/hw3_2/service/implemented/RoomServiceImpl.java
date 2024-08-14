package com.example.hw3_2.service.implemented;

import com.example.hw3_2.db.ConnectionManager;
import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.RoomService;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();
    @Override
    public List<Room> findAll() {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Room> cq = cb.createQuery(Room.class);
        Root<Room> rootEntry = cq.from(Room.class);
        CriteriaQuery<Room> all = cq.select(rootEntry);
        TypedQuery<Room> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Room findById(Integer id) throws NotFoundException {
        Session session = connectionManager.getSessionFactory().openSession();
        Room room = session.get(Room.class, id);
        session.flush();
        session.close();
        if (room == null) {
            throw new NotFoundException("Room not found by id");
        }
        return room;
    }

    @Override
    public void save(Room room) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.persist(room);
        session.flush();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = connectionManager.getSessionFactory().openSession();
        Room room = session.get(Room.class, id);
        session.delete(room);
        session.flush();
        session.close();
    }

    @Override
    public void update(Room room) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.update(room);
        session.flush();
        session.close();
    }
}
