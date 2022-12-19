package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {
    public List<Balloon> findAllBalloons() {
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloons.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Balloon findById(Long id) {
        return DataHolder.balloons.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst().orElse(null);
    }

    public Long saveBalloon(Long id, String name, String description, Manufacturer manufacturer) {
        Long failed = (long) -1;
        if (name == null || name.isEmpty()) return failed;
        if (description == null || description.isEmpty()) return failed;
        if (manufacturer == null) return failed;

        Balloon balloon = this.findById(id);
        if (balloon != null) {
            balloon.setName(name);
            balloon.setDescription(description);
        }
        else {
            balloon = new Balloon(name, description, manufacturer);
            DataHolder.balloons.add(balloon);
        }

        return balloon.getId();
    }

    public boolean deleteBalloon(Long id) {
        if (id == null) return false;

        boolean removed = DataHolder.balloons.removeIf(e -> e.getId().equals(id));
        if (removed) DataHolder.deletedBalloons++;

        return removed;
    }

    public int getCountOfDeletedBalloons() {
        return DataHolder.deletedBalloons;
    }
}
