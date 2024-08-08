package com.example.servlet;

import com.example.exception.NotFoundException;
import com.example.model.Booking;
import com.example.model.Rate;
import com.example.model.Room;
import com.example.repository.BookingRepository;
import com.example.repository.RateRepository;

import com.example.repository.implemented.BookingRepositoryImpl;
import com.example.repository.implemented.RateRepositoryImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/booking/*"})
public class BookingServlet extends HttpServlet {
    private final transient BookingRepository bookingRepository = BookingRepositoryImpl.getInstance();
    private final ObjectMapper objectMapper;

    public BookingServlet() {
        this.objectMapper = new ObjectMapper();
    }

    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = req.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<Booking> booking = bookingRepository.findAll();
                resp.setStatus(HttpServletResponse.SC_OK);
                StringBuilder sb = new StringBuilder();
                for (Booking b : booking) {
                    sb.append(b.toString());
                    sb.append("\n");
                }
                responseAnswer = sb.toString();
            } else {
                Integer bookingId = Integer.parseInt(pathPart[1]);
                Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                        new NotFoundException("Room not found."));
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = booking.toString();
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            Integer bookingId = Integer.parseInt(pathPart[1]);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            bookingRepository.deleteById(bookingId);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request. ";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = null;
        Optional<Booking> bookingResponse;
        try {
            bookingResponse = Optional.ofNullable(objectMapper.readValue(json, Booking.class));
            Booking booking = bookingResponse.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(bookingRepository.add(booking));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect booking Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<Booking> bookingResponce;
        try {
            bookingResponce = Optional.ofNullable(objectMapper.readValue(json, Booking.class));
            Booking booking = bookingResponce.orElseThrow(IllegalArgumentException::new);
            bookingRepository.update(booking);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect booking Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
