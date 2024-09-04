package homework.hw4.service;

import homework.hw4.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeService extends CrudRepository<Employee, Integer> {
}
