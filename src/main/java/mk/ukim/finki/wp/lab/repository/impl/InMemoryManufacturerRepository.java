package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Manufacturer findById(Long id) {
        return DataHolder.manufacturers.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst().orElse(null);
    }
}
