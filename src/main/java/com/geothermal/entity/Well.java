package com.geothermal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "wells")
public class Well {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wellId;

    @NotBlank
    private String wellName;

    @NotBlank
    private String location;

    @Positive
    private Double depth;

    private Double temperature;
    private Double pressure;

    @NotBlank
    private String status;

    public Well() {}

    public Long getWellId() { return wellId; }
    public void setWellId(Long wellId) { this.wellId = wellId; }

    public String getWellName() { return wellName; }
    public void setWellName(String wellName) { this.wellName = wellName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getDepth() { return depth; }
    public void setDepth(Double depth) { this.depth = depth; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getPressure() { return pressure; }
    public void setPressure(Double pressure) { this.pressure = pressure; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
