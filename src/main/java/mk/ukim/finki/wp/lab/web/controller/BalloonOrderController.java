package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/BalloonOrder.do")
public class BalloonOrderController {

    private final BalloonService balloonService;
    private final UserService userService;

    public BalloonOrderController(BalloonService balloonService, UserService userService) {
        this.balloonService = balloonService;
        this.userService = userService;
    }

    @GetMapping
    public String getDoOrder(HttpServletRequest req, Model model) {
        Long balloonId = (Long)req.getSession().getAttribute("balloonId");
        String balloonSize = (String)req.getSession().getAttribute("size");
        if (balloonId == null || Objects.equals(balloonSize, null)) {
            return "redirect:/selectBalloon?error=MissingAttributes";
        }

        Balloon balloon = balloonService.findById(balloonId);
        if (balloon == null) {
            return "redirect:/selectBalloon?error=MissingAttributes";
        }

        model.addAttribute("color", balloon.getName());
        model.addAttribute("size", balloonSize);

        model.addAttribute("users", userService.listAll());

        return "deliveryInfo";
    }

    @PostMapping
    public String doOrder(@RequestParam(required = false) String color, @RequestParam(required = false) String size, HttpServletRequest req, Model model) {
        if (Objects.equals(color, null) || Objects.equals(size, null)) {
            return "redirect:/selectBalloon?error=MissingAttributes";
        }

        req.getSession().setAttribute("size", size);

        model.addAttribute("color", color);
        model.addAttribute("size", size);

        model.addAttribute("users", userService.listAll());

        return "deliveryInfo";
    }
}
