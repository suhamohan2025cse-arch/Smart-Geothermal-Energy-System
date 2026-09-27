package com.geothermal.controller;

import com.geothermal.entity.EnergySale;
import com.geothermal.service.EnergySaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geothermal/sales")
public class EnergySaleController {

    private final EnergySaleService service;

    public EnergySaleController(EnergySaleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnergySale create(@Valid @RequestBody EnergySale sale) {
        return service.create(sale);
    }

    @GetMapping
    public List<EnergySale> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EnergySale getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EnergySale update(@PathVariable Long id, @Valid @RequestBody EnergySale sale) {
        return service.update(id, sale);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
