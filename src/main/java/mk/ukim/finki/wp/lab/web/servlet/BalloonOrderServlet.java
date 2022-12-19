package mk.ukim.finki.wp.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet (name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        String size = req.getParameter("size");

        req.getSession().setAttribute("size", size);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("color", color);
        context.setVariable("size", size);
        this.springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }
}
