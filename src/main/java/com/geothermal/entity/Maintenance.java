package com.geothermal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceId;

    @NotNull
    private Long wellId;

    @NotBlank
    private String vendor;

    @NotBlank
    private String description;

    @PositiveOrZero
    private Double cost;

    private LocalDate maintenanceDate;

    @NotBlank
    private String status;

    public Maintenance() {}

    public Long getMaintenanceId() { return maintenanceId; }
    public void setMaintenanceId(Long maintenanceId) { this.maintenanceId = maintenanceId; }

    public Long getWellId() { return wellId; }
    public void setWellId(Long wellId) { this.wellId = wellId; }

    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public LocalDate getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDate maintenanceDate) { this.maintenanceDate = maintenanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
