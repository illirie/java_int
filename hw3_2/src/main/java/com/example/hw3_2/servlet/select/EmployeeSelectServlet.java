package com.example.hw3_2.servlet.select;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Employee;
import com.example.hw3_2.service.EmployeeService;
import com.example.hw3_2.service.implemented.EmployeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hw3_2_war_exploded/employee/SelectEmployee")
public class EmployeeSelectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            String message = "";
            switch (request.getParameter("select")) {
                case "all":
                    List<Employee> employees = employeeService.findAll();
                    message = employees.toString();
                    break;
                case "by_id":
                    Employee employee = employeeService.findById(Integer.parseInt(request.getParameter("id")));
                    message = employee.toString();
                    break;
            }
            response.sendRedirect("Success.jsp?msg=" + message);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
