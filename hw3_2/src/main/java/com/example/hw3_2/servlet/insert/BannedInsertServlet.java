package com.example.hw3_2.servlet.insert;

import com.example.hw3_2.model.Banned;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.service.BannedService;
import com.example.hw3_2.service.implemented.BannedServiceImpl;
import com.example.hw3_2.service.implemented.RateServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/banned/InsertBanned")
public class BannedInsertServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            BannedService bannedService = new BannedServiceImpl();

            Banned banned = new Banned();
            banned.setFirst_name(request.getParameter("first_name"));
            banned.setLast_name(request.getParameter("last_name"));
            banned.setEmail(request.getParameter("email"));
            banned.setPhone(request.getParameter("phone"));
            banned.setBannedBy(request.getParameter("reason"));

            bannedService.save(banned);

            // Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Insert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
