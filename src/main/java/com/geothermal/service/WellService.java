package com.geothermal.service;

import com.geothermal.entity.Well;
import com.geothermal.repository.WellRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WellService {

    private final WellRepository repository;

    public WellService(WellRepository repository) {
        this.repository = repository;
    }

    public Well create(Well well) {
        return repository.save(well);
    }

    public List<Well> getAll() {
        return repository.findAll();
    }

    public Well getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Well not found with id: " + id));
    }

    public Well update(Long id, Well input) {
        Well well = getById(id);
        well.setWellName(input.getWellName());
        well.setLocation(input.getLocation());
        well.setDepth(input.getDepth());
        well.setTemperature(input.getTemperature());
        well.setPressure(input.getPressure());
        well.setStatus(input.getStatus());
        return repository.save(well);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
