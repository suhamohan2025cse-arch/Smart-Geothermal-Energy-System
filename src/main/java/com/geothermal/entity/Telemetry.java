package com.geothermal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telemetryId;

    @NotNull
    private Long wellId;

    @NotNull
    private Double temperature;

    @NotNull
    private Double pressure;

    @NotNull
    @PositiveOrZero
    private Double flowRate;

    private String pressureStatus;

    private LocalDateTime recordedAt;

    public Telemetry() {}

    public Long getTelemetryId() { return telemetryId; }
    public void setTelemetryId(Long telemetryId) { this.telemetryId = telemetryId; }

    public Long getWellId() { return wellId; }
    public void setWellId(Long wellId) { this.wellId = wellId; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getPressure() { return pressure; }
    public void setPressure(Double pressure) { this.pressure = pressure; }

    public Double getFlowRate() { return flowRate; }
    public void setFlowRate(Double flowRate) { this.flowRate = flowRate; }

    public String getPressureStatus() { return pressureStatus; }
    public void setPressureStatus(String pressureStatus) { this.pressureStatus = pressureStatus; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
