package com.geothermal.service;

import com.geothermal.entity.Maintenance;
import com.geothermal.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repository;

    public MaintenanceService(MaintenanceRepository repository) {
        this.repository = repository;
    }

    public Maintenance create(Maintenance maintenance) {
        if (maintenance.getMaintenanceDate() == null) {
            maintenance.setMaintenanceDate(LocalDate.now());
        }
        if (maintenance.getStatus() == null || maintenance.getStatus().isBlank()) {
            maintenance.setStatus("PENDING");
        }
        return repository.save(maintenance);
    }

    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    public List<Maintenance> getRecent() {
        return repository.findTop10ByOrderByMaintenanceIdDesc();
    }

    public Maintenance getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance record not found with id: " + id));
    }

    public Maintenance update(Long id, Maintenance input) {
        Maintenance maintenance = getById(id);
        maintenance.setWellId(input.getWellId());
        maintenance.setVendor(input.getVendor());
        maintenance.setDescription(input.getDescription());
        maintenance.setCost(input.getCost());
        if (input.getMaintenanceDate() != null) {
            maintenance.setMaintenanceDate(input.getMaintenanceDate());
        }
        if (input.getStatus() != null && !input.getStatus().isBlank()) {
            maintenance.setStatus(input.getStatus());
        }
        return repository.save(maintenance);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Maintenance record not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
