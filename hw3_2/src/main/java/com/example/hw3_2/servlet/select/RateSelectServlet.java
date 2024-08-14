package com.example.hw3_2.servlet.select;

import com.example.hw3_2.exception.NotFoundException;
import com.example.hw3_2.model.Banned;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.service.RateService;
import com.example.hw3_2.service.implemented.RateServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hw3_2_war_exploded/rates/SelectRate")
public class RateSelectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RateService rateService = new RateServiceImpl();
            String message = "";
            switch (request.getParameter("select")) {
                case "all":
                    List<Rate> banned = rateService.findAll();
                    message = banned.toString();
                    break;
                case "by_id":
                    Rate banned1 = rateService.findById(Integer.parseInt(request.getParameter("id")));
                    message = banned1.toString();
                    break;
            }
            response.sendRedirect("Success.jsp?msg="+ message);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
