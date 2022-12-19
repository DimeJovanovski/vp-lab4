package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet (name = "SelectBalloonServlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        req.getSession().setAttribute("color", color);
        req.getSession().setAttribute("loggedIn", true);

        if (color == null) {
            resp.sendRedirect("/balloons");
        }

        List<Balloon> balloons = balloonService.searchByNameOrDescription(color);
        Long balloonId = balloons.size() > 0 ? balloons.get(0).getId() : (long) -1;
        req.getSession().setAttribute("balloonId", balloonId);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("color", color);
        this.springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
    }
}
