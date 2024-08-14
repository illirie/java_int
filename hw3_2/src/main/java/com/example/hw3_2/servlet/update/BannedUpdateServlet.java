package com.example.hw3_2.servlet.update;

import com.example.hw3_2.model.Banned;
import com.example.hw3_2.service.BannedService;
import com.example.hw3_2.service.implemented.BannedServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/banned/UpdateBanned")
public class BannedUpdateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            BannedService bannedService = new BannedServiceImpl();

            Banned banned = bannedService.findById(Integer.parseInt(request.getParameter("id")));
            banned.setFirst_name(request.getParameter("first_name"));
            banned.setLast_name(request.getParameter("last_name"));
            banned.setEmail(request.getParameter("email"));
            banned.setPhone(request.getParameter("phone"));
            banned.setBannedBy(request.getParameter("reason"));

            bannedService.update(banned);
            response.sendRedirect("Success.jsp?msg=Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
