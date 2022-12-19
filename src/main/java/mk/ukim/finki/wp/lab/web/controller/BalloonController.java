package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/", "/balloons"})
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        model.addAttribute("balloons", this.balloonService.listAll());
        model.addAttribute("deletedBalloons", this.balloonService.getCountOfDeletedBalloons());
        return "listBalloons";
    }

    @PostMapping("/search")
    public String searchBalloon(@RequestParam(required = false) String searchQuery, Model model) {
        model.addAttribute("balloons", this.balloonService.searchByNameOrDescription(searchQuery));
        model.addAttribute("deletedBalloons", this.balloonService.getCountOfDeletedBalloons());
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id, @RequestParam String color, @RequestParam String description, @RequestParam Long manufacturerId, Model model) {
        if (id == null) id = (long) -1;
        Long balloonId = this.balloonService.saveBalloon(id, color, description, manufacturerService.findById(manufacturerId));

        model.addAttribute("balloons", this.balloonService.listAll());
        model.addAttribute("deletedBalloons", this.balloonService.getCountOfDeletedBalloons());
        return "listBalloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id, Model model) {
        this.balloonService.deleteBalloon(id);

        model.addAttribute("balloons", this.balloonService.listAll());
        model.addAttribute("deletedBalloons", this.balloonService.getCountOfDeletedBalloons());
        return "listBalloons";
    }
}
