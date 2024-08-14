package com.example.hw3_2.servlet.select;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.RoomService;
import com.example.hw3_2.service.implemented.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hw3_2_war_exploded/rooms/SelectRoom")
public class RoomSelectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RoomService roomService = new RoomServiceImpl();
            String message = "";
            switch (request.getParameter("select")) {
                case "all":
                    List<Room> rooms = roomService.findAll();
                    message = rooms.toString();
                    break;
                case "by_id":
                    Room room = roomService.findById(Integer.parseInt(request.getParameter("id")));
                    message = room.toString();
                    break;
            }
            response.sendRedirect("Success.jsp?msg="+ message);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
