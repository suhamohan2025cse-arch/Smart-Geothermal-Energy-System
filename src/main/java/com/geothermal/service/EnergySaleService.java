package com.geothermal.service;

import com.geothermal.entity.EnergySale;
import com.geothermal.repository.EnergySaleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EnergySaleService {

    private final EnergySaleRepository repository;

    public EnergySaleService(EnergySaleRepository repository) {
        this.repository = repository;
    }

    public EnergySale create(EnergySale sale) {
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDate.now());
        }
        if (sale.getStatus() == null || sale.getStatus().isBlank()) {
            sale.setStatus("PENDING");
        }
        return repository.save(sale);
    }

    public List<EnergySale> getAll() {
        return repository.findAll();
    }

    public List<EnergySale> getRecent() {
        return repository.findTop10ByOrderBySaleIdDesc();
    }

    public EnergySale getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Energy sale not found with id: " + id));
    }

    public EnergySale update(Long id, EnergySale input) {
        EnergySale sale = getById(id);
        sale.setCustomerId(input.getCustomerId());
        sale.setEnergyGJ(input.getEnergyGJ());
        sale.setAmount(input.getAmount());
        if (input.getSaleDate() != null) {
            sale.setSaleDate(input.getSaleDate());
        }
        if (input.getStatus() != null && !input.getStatus().isBlank()) {
            sale.setStatus(input.getStatus());
        }
        return repository.save(sale);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Energy sale not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
