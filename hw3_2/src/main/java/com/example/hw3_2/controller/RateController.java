package com.example.hw3_2.controller;
import com.example.hw3_2.model.Rate;
import com.example.hw3_2.service.RateService;
import com.example.hw3_2.service.implemented.RateServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RateController {
    @RequestMapping(value = "/hw3_2_war_exploded/rates/UpdateRate", method = RequestMethod.POST)
    public ModelAndView updateRate() {
        try {
            RateService rateService = new RateServiceImpl();
            Rate rate = rateService.findById(Integer.parseInt(request.getParameter("id")));
            rate.setMaxPersons(Integer.parseInt(request.getParameter("max_persons")));
            rate.setPrice(Double.parseDouble(request.getParameter("price")));
            rateService.update(rate);

            response.sendRedirect("Success.jsp?msg=Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        return modelAndView;
    }
}
