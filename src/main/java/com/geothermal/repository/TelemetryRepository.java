package com.geothermal.repository;

import com.geothermal.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
    List<Telemetry> findByWellIdOrderByRecordedAtDesc(Long wellId);

    List<Telemetry> findTop10ByOrderByRecordedAtDesc();

    List<Telemetry> findByPressureStatusOrderByRecordedAtDesc(String pressureStatus);
}
