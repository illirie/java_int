package com.example.hw3_2.servlet.select;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Guest;
import com.example.hw3_2.model.Person;
import com.example.hw3_2.model.Room;
import com.example.hw3_2.service.GuestService;
import com.example.hw3_2.service.implemented.GuestServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hw3_2_war_exploded/guests/SelectGuest")
public class GuestSelectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GuestService guestService = new GuestServiceImpl();
            String message = "";
            switch (request.getParameter("select")) {
                case "all":
                    List<Guest> guests = guestService.findAll();
                    message = guests.toString();
                    break;
                case "by_id":
                    Guest guest = guestService.findById(Integer.parseInt(request.getParameter("id")));
                    message = guest.toString();
                    break;
                case "by_name":
                    List<Person> persons = guestService.findAllPersonsByName(request.getParameter("name"));
                    message = persons.toString();
                    break;
                case "all_rooms":
                    List<Room> rooms = guestService.findAllRooms(guestService.
                            findById(Integer.parseInt(request.getParameter("id"))));
                    message = rooms.toString();
            }
            response.sendRedirect("Success.jsp?msg=" + message);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
