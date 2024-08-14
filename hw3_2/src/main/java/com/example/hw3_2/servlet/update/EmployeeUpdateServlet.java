package com.example.hw3_2.servlet.update;

import com.example.hw3_2.model.Employee;
import com.example.hw3_2.service.EmployeeService;
import com.example.hw3_2.service.implemented.EmployeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/employee/UpdateEmployee")
public class EmployeeUpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();

            Employee employee = employeeService.findById(Integer.parseInt(request.getParameter("id")));
            employee.setFirst_name(request.getParameter("first_name"));
            employee.setLast_name(request.getParameter("last_name"));
            employee.setEmail(request.getParameter("email"));
            employee.setPhone(request.getParameter("phone"));
            employee.setSalary(Double.parseDouble(request.getParameter("salary")));
            employee.setWorkingHours(Integer.parseInt(request.getParameter("working_hours")));

            employeeService.update(employee);

            // Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
