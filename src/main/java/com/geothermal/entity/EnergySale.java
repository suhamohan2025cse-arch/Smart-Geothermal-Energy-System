package com.geothermal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "energy_sales")
public class EnergySale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @NotNull
    private Long customerId;

    @NotNull
    @Positive
    private Double energyGJ;

    @NotNull
    @PositiveOrZero
    private Double amount;

    private LocalDate saleDate;

    @NotBlank
    private String status;

    public EnergySale() {}

    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Double getEnergyGJ() { return energyGJ; }
    public void setEnergyGJ(Double energyGJ) { this.energyGJ = energyGJ; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
