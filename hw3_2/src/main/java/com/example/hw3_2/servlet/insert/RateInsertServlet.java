package com.example.hw3_2.servlet.insert;

import com.example.hw3_2.model.Rate;
import com.example.hw3_2.service.RateService;
import com.example.hw3_2.service.implemented.RateServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hw3_2_war_exploded/rates/InsertRate")
public class RateInsertServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Jdbc Connection
        try {
            RateService rateService = new RateServiceImpl();

            Rate rate = new Rate();
            rate.setMaxPersons(Integer.parseInt(request.getParameter("max_persons")));
            rate.setPrice(Double.parseDouble(request.getParameter("price")));

            rateService.save(rate);

            // Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Insert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
