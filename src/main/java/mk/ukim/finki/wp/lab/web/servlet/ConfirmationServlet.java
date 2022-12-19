package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

//@WebServlet (name = "ConfirmationServlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final BalloonService balloonService;

    public ConfirmationServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.balloonService = balloonService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        String size = (String) req.getSession().getAttribute("size");
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        LocalDateTime datetime_local = LocalDateTime.parse(req.getParameter("datetime_local"));

        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        Long balloonId = (long) req.getSession().getAttribute("balloonId");
        Balloon balloon = balloonService.findById(balloonId);
//        orderService.placeOrder(balloon, clientName, datetime_local);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("color", color);
        context.setVariable("size", size);
        context.setVariable("clientName", clientName);
        context.setVariable("clientAddress", clientAddress);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("clientAgent", clientAgent);
        context.setVariable("datetime_local", datetime_local);
        this.springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }
}
