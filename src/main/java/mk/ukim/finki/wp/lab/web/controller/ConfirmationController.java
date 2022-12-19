package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping("/confirmationInfo")
public class ConfirmationController {
    private final OrderService orderService;
    private final BalloonService balloonService;
    private final UserService userService;

    public ConfirmationController(OrderService orderService, BalloonService balloonService, UserService userService) {
        this.orderService = orderService;
        this.balloonService = balloonService;
        this.userService = userService;
    }

    @PostMapping
    public String confirm(@RequestParam(required = false) String color, @RequestParam(required = false) Long userId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime_local, HttpServletRequest req, Model model) {
        String size = (String) req.getSession().getAttribute("size");
        if (Objects.equals(color, null) || Objects.equals(size, null)  || Objects.equals(userId, null) || Objects.equals(datetime_local, null)) {
            return "redirect:/BalloonOrder.do?error=MissingAttributes";
        }

        LocalDateTime localDateTime = datetime_local;

        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        Long balloonId = (Long) req.getSession().getAttribute("balloonId");
        Balloon balloon = balloonService.findById(balloonId);
        if (balloon == null) {
            return "redirect:/BalloonOrder.do?error=BalloonDoesntExist";
        }

        User user = userService.findById(userId);
        if (user == null) {
            return "redirect:/BalloonOrder.do?error=UserDoesntExist";
        }

        orderService.placeOrder(balloon, user, localDateTime);

        model.addAttribute("color", balloon.getName());
        model.addAttribute("size", size);
        model.addAttribute("clientName", user.getUsername());
        model.addAttribute("ipAddress", ipAddress);
        model.addAttribute("clientAgent", clientAgent);
        model.addAttribute("datetime_local", localDateTime);

        return "confirmationInfo";
    }
}
