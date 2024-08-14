package com.example.hw3_2.servlet.update;

import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.RateService;
import com.example.hw3_2.service.RoomService;
import com.example.hw3_2.service.implemented.RateServiceImpl;
import com.example.hw3_2.service.implemented.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/hw3_2_war_exploded/rooms/UpdateRoom")
public class RoomUpdateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            RoomService roomService = new RoomServiceImpl();
            RateService rateService = new RateServiceImpl();

            Room room = roomService.findById(Integer.parseInt(request.getParameter("id")));
            room.setRate(rateService.findById(Integer.parseInt(request.getParameter("rate"))));
            roomService.update(room);

            // Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
