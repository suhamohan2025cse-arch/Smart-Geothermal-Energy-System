package com.geothermal.service;

import com.geothermal.entity.Telemetry;
import com.geothermal.repository.TelemetryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelemetryService {

    private static final double PRESSURE_THRESHOLD = 20.0;

    private final TelemetryRepository repository;

    public TelemetryService(TelemetryRepository repository) {
        this.repository = repository;
    }

    public Telemetry create(Telemetry telemetry) {
        telemetry.setRecordedAt(LocalDateTime.now());

        if (telemetry.getPressure() < PRESSURE_THRESHOLD) {
            telemetry.setPressureStatus("WARNING - PRESSURE DROP");
        } else {
            telemetry.setPressureStatus("NORMAL");
        }

        return repository.save(telemetry);
    }

    public List<Telemetry> getAll() {
        return repository.findAll();
    }

    public List<Telemetry> getByWell(Long wellId) {
        return repository.findByWellIdOrderByRecordedAtDesc(wellId);
    }
}
