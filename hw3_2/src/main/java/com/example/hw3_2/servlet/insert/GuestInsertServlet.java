package com.example.hw3_2.servlet.insert;

import com.example.hw3_2.model.Guest;
import com.example.hw3_2.service.GuestService;
import com.example.hw3_2.service.implemented.GuestServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/hw3_2_war_exploded/guests/InsertGuest")
public class GuestInsertServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            GuestService guestService = new GuestServiceImpl();

            Guest guest = new Guest();
            guest.setFirst_name(request.getParameter("first_name"));
            guest.setLast_name(request.getParameter("last_name"));
            guest.setEmail(request.getParameter("email"));
            guest.setPhone(request.getParameter("phone"));
            guest.setStartDate(Date.valueOf(request.getParameter("start_date")));
            guest.setEndDate(Date.valueOf(request.getParameter("end_date")));

            guestService.save(guest);

            // Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Insert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
