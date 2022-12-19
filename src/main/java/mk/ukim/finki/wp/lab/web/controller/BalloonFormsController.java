package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balloon-forms")
public class BalloonFormsController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonFormsController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/add-balloon-form")
    public String getAddBalloonForm(Model model) {

        model.addAttribute("manufacturers",  manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/delete-balloon-form")
    public String getDeleteBalloonForm(Model model) {

        model.addAttribute("balloons",  balloonService.listAll());
        return "delete-balloon";
    }

    @GetMapping("/edit-balloon-form/{id}")
    public String getEditBalloonForm(@PathVariable Long id, Model model) {
        model.addAttribute("edit", true);

        Balloon balloon = balloonService.findById(id);
        if (balloon == null) {
            model.addAttribute("errorBalloonId", id);
            model.addAttribute("error", "EditIdNotFound");
            model.addAttribute("balloons",  balloonService.listAll());
            return "listBalloons";
        }

        model.addAttribute("balloon", balloon);

        model.addAttribute("manufacturers",  manufacturerService.findAll());
        return "add-balloon";
    }
}
