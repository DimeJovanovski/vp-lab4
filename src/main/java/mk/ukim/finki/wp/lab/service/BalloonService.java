package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);

    Balloon findById(Long id);

    Long saveBalloon(Long id, String name, String description, Manufacturer manufacturer);

    void deleteBalloon(Long id);

    int getCountOfDeletedBalloons();
}
