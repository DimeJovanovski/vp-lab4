package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepository;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text, text);
    }

    @Override
    public Balloon findById(Long id) {
        return balloonRepository.findById(id).orElse(null);
    }

    @Override
    public Long saveBalloon(Long id, String name, String description, Manufacturer manufacturer) {
        Balloon balloon = new Balloon(name, description, manufacturer);
        balloonRepository.save(balloon);

        return balloon.getId();
    }

    @Override
    public void deleteBalloon(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public int getCountOfDeletedBalloons() {
        return 0;
    }
//    public int getCountOfDeletedBalloons() {
//        return balloonRepository.getCountOfDeletedBalloons();
//    }
}
