package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ManufacturerService manufacturerService;

    public OrderController(OrderService orderService, ManufacturerService manufacturerService) {
        this.orderService = orderService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getOrders(HttpServletRequest req, Model model) {
        model.addAttribute("orders", orderService.listAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "userOrders";
    }
}
