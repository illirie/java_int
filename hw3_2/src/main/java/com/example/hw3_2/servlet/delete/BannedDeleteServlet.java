package com.example.hw3_2.servlet.delete;

import com.example.hw3_2.service.BannedService;
import com.example.hw3_2.service.implemented.BannedServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/banned/DeleteBanned")
public class BannedDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BannedService bannedService = new BannedServiceImpl();
            bannedService.deleteById(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Success.jsp?msg=Delete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
