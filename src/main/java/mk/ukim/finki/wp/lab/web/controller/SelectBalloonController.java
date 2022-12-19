package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/selectBalloon")
public class SelectBalloonController {
    private final BalloonService balloonService;

    public SelectBalloonController(BalloonService balloonService) {
        this.balloonService = balloonService;
    }

    @GetMapping
    public String selectBalloon(HttpServletRequest req, Model model) {
        Long balloonId = (Long)req.getSession().getAttribute("balloonId");
        if (balloonId == null) {
            return "redirect:/balloons?error=NoColorSelected";
        }

        Balloon balloon = balloonService.findById(balloonId);
        if (balloon == null) {
            return "redirect:/balloons?error=BalloonDoesntExist";
        }

        model.addAttribute("color", balloon.getName());
        req.getSession().setAttribute("loggedIn", true);

        return "selectBalloonSize";
    }

    @PostMapping
    public String selectBalloon(@RequestParam(required = false) String color, HttpServletRequest req, Model model) {
        String the_color = color;
        if (Objects.equals(the_color, null)) {
            return "redirect:/balloons?error=NoColorSelected";
        }

        List<Balloon> balloons = balloonService.searchByNameOrDescription(the_color);
        long balloonId = balloons.size() > 0 ? balloons.get(0).getId() : (long) -1;

        if (balloonId == -1) {
            return "redirect:/balloons?error=BalloonDoesntExist";
        }

        req.getSession().setAttribute("balloonId", balloonId);
        model.addAttribute("color", the_color);

        return "selectBalloonSize";
    }
}
