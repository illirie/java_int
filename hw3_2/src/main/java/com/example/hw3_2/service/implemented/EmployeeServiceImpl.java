package com.example.hw3_2.service.implemented;

import com.example.hw3_2.db.ConnectionManager;
import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Employee;
import com.example.hw3_2.service.EmployeeService;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static ConnectionManager connectionManager = ConnectionManager.getInstance();
    @Override
    public List<Employee> findAll() {
        Session session = connectionManager.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> rootEntry = cq.from(Employee.class);
        CriteriaQuery<Employee> all = cq.select(rootEntry);
        TypedQuery<Employee> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Employee findById(Integer id) throws NotFoundException {
        Session session = connectionManager.getSessionFactory().openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        if (employee == null) {
            throw new NotFoundException("Employee not found by id");
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.persist(employee);
        session.flush();
        session.close();

    }

    @Override
    public void update(Employee employee) {
        Session session = connectionManager.getSessionFactory().openSession();
        session.update(employee);
        session.flush();
        session.close();

    }

    @Override
    public void deleteById(Integer id) {
        Session session = connectionManager.getSessionFactory().openSession();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        session.flush();
        session.close();

    }
}
