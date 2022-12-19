package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    public List<Manufacturer> findAll();

    public Manufacturer findById(Long id);
}
