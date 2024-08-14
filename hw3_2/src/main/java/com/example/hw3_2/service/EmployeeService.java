package com.example.hw3_2.service;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Employee;

import java.util.List;

public interface EmployeeService extends Service <Employee, Integer> {
    List<Employee> findAll();
    Employee findById(Integer id) throws NotFoundException;
    void save(Employee employee);
    void update(Employee employee);
    void deleteById(Integer id);
}
