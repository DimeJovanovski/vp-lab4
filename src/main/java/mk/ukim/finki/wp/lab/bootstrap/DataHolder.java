package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {
    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static int deletedBalloons = 0;

//    @PostConstruct
//    public void init() {
//        List<String> names = new ArrayList<String>(Arrays.asList("Theodor", "Roosevelt", "John", "Tony", "Steve"));
//        names.forEach(e -> manufacturers.add(new Manufacturer(e, e + "landia", e + " Street")));
//
//        List<String> colors = new ArrayList<String>(Arrays.asList("Red", "Yellow", "Green", "Blue", "Tangerine", "Purple", "Pink", "White", "Black", "Brown"));
//        colors.forEach(e -> balloons.add(new Balloon(e + " Balloon", "A nice " + e.toLowerCase() + " balloon.", manufacturers.get((int)(Math.random() * manufacturers.size())))));
//    }
}
