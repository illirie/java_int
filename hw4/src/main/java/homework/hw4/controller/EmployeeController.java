package homework.hw4.controller;

import homework.hw4.model.Employee;
import homework.hw4.model.Guest;
import homework.hw4.service.EmployeeService;
import homework.hw4.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employee/")
    public List<Employee> showEmployee() {
        return (List<Employee>) employeeService.findAll();
    }

    @GetMapping(value = "/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.findById(id).orElse(null);
    }

    @GetMapping(value = "/employee/{id}/delete")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
    }
}
