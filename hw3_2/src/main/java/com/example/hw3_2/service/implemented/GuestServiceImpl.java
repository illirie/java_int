package com.example.hw3_2.service.implemented;

import com.example.hw3_2.db.ConnectionManager;
import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.model.Person;
import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.GuestService;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GuestServiceImpl implements GuestService {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();
    @Override
    public List<Guest> findAll() {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Guest> cq = cb.createQuery(Guest.class);
        Root<Guest> rootEntry = cq.from(Guest.class);
        CriteriaQuery<Guest> all = cq.select(rootEntry);
        TypedQuery<Guest> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Guest findById(Integer id) throws NotFoundException {
        Session session = connectionManager.getSessionFactory().openSession();
        Guest guest = session.get(Guest.class, id);
        session.flush();
        session.close();
        if (guest == null) {
            throw new NotFoundException("Guest not found by id");
        }
        return guest;
    }

    @Override
    public void save(Guest guest) {
        Session session = connectionManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(guest);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = connectionManager.getSessionFactory().openSession();
        Guest guest = session.get(Guest.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(guest);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Guest guest) {
        Session session = connectionManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(guest);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Room> findAllRooms(Guest guest) {
        Session session = connectionManager.getSessionFactory().openSession();
        guest = session.get(Guest.class, guest.getId());
        return guest.getRooms();
    }

    @Override
    public void addReservation(Guest guest, Room room) {
        Session session = connectionManager.getSessionFactory().openSession();
        guest.addRoom(room);
        session.update(guest);
        session.flush();
        session.close();
    }

    @Override
    public List<Person> findAllPersonsByName(String name) {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.where(
                cb.equal(root.get("first_name"), name)
        );

        return session
                .createQuery(criteria)
                .getResultList();
    }
}
