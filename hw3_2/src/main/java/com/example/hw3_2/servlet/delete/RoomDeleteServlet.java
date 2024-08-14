package com.example.hw3_2.servlet.delete;

import com.example.hw3_2.service.RoomService;
import com.example.hw3_2.service.implemented.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/rooms/DeleteRoom")
public class RoomDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RoomService roomService = new RoomServiceImpl();
            roomService.deleteById(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Success.jsp?msg=Delete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}