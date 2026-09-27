package com.geothermal.controller;

import com.geothermal.entity.Well;
import com.geothermal.service.WellService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geothermal/wells")
public class WellController {

    private final WellService service;

    public WellController(WellService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Well create(@Valid @RequestBody Well well) {
        return service.create(well);
    }

    @GetMapping
    public List<Well> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Well getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Well update(@PathVariable Long id, @Valid @RequestBody Well well) {
        return service.update(id, well);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
