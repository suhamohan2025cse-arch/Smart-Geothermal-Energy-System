package com.geothermal.controller;

import com.geothermal.entity.Telemetry;
import com.geothermal.service.TelemetryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/geothermal/telemetry")
public class TelemetryController {

    private final TelemetryService service;

    public TelemetryController(TelemetryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telemetry create(@Valid @RequestBody Telemetry telemetry) {
        return service.create(telemetry);
    }

    @GetMapping
    public List<Telemetry> getAll() {
        return service.getAll();
    }

    @GetMapping("/well/{wellId}")
    public List<Telemetry> getByWell(@PathVariable Long wellId) {
        return service.getByWell(wellId);
    }
}
