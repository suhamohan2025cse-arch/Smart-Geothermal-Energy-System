package com.geothermal.controller;

import com.geothermal.entity.EnergySale;
import com.geothermal.entity.Maintenance;
import com.geothermal.entity.Telemetry;
import com.geothermal.entity.Well;
import com.geothermal.repository.CustomerRepository;
import com.geothermal.repository.EnergySaleRepository;
import com.geothermal.repository.MaintenanceRepository;
import com.geothermal.repository.TelemetryRepository;
import com.geothermal.repository.WellRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/geothermal/reports")
public class ReportController {

    private static final double PRESSURE_THRESHOLD = 20.0;
    private static final String WARNING_STATUS = "WARNING - PRESSURE DROP";

    private final EnergySaleRepository saleRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final WellRepository wellRepository;
    private final CustomerRepository customerRepository;
    private final TelemetryRepository telemetryRepository;

    public ReportController(EnergySaleRepository saleRepository,
                            MaintenanceRepository maintenanceRepository,
                            WellRepository wellRepository,
                            CustomerRepository customerRepository,
                            TelemetryRepository telemetryRepository) {
        this.saleRepository = saleRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.wellRepository = wellRepository;
        this.customerRepository = customerRepository;
        this.telemetryRepository = telemetryRepository;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        List<EnergySale> sales = saleRepository.findAll();
        List<Maintenance> maintenance = maintenanceRepository.findAll();

        double totalEnergy = sales.stream()
                .mapToDouble(EnergySale::getEnergyGJ)
                .sum();

        double totalRevenue = sales.stream()
                .mapToDouble(EnergySale::getAmount)
                .sum();

        double totalMaintenanceExpense = maintenance.stream()
                .mapToDouble(Maintenance::getCost)
                .sum();

        double netOperationalAmount = totalRevenue - totalMaintenanceExpense;

        Map<String, Object> result = new HashMap<>();
        result.put("totalEnergySoldGJ", totalEnergy);
        result.put("totalRevenue", totalRevenue);
        result.put("maintenanceExpense", totalMaintenanceExpense);
        result.put("netOperationalAmount", netOperationalAmount);

        return result;
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        List<Well> wells = wellRepository.findAll();
        List<EnergySale> sales = saleRepository.findAll();
        List<Maintenance> maintenance = maintenanceRepository.findAll();

        long normalWells = wells.stream()
                .filter(w -> w.getPressure() == null || w.getPressure() >= PRESSURE_THRESHOLD)
                .count();

        long warningWells = wells.size() - normalWells;

        double totalEnergy = sales.stream()
                .mapToDouble(EnergySale::getEnergyGJ)
                .sum();

        double totalRevenue = sales.stream()
                .mapToDouble(EnergySale::getAmount)
                .sum();

        double totalMaintenanceExpense = maintenance.stream()
                .mapToDouble(Maintenance::getCost)
                .sum();

        double netOperationalAmount = totalRevenue - totalMaintenanceExpense;

        Map<String, Object> result = new HashMap<>();
        result.put("totalWells", wells.size());
        result.put("normalWells", normalWells);
        result.put("warningWells", warningWells);
        result.put("totalCustomers", customerRepository.count());
        result.put("totalEnergySoldGJ", totalEnergy);
        result.put("totalRevenue", totalRevenue);
        result.put("maintenanceExpense", totalMaintenanceExpense);
        result.put("netOperationalAmount", netOperationalAmount);

        return result;
    }

    @GetMapping("/recent-telemetry")
    public List<Telemetry> recentTelemetry() {
        return telemetryRepository.findTop10ByOrderByRecordedAtDesc();
    }

    @GetMapping("/pressure-warnings")
    public List<Telemetry> pressureWarnings() {
        return telemetryRepository.findByPressureStatusOrderByRecordedAtDesc(WARNING_STATUS);
    }

    @GetMapping("/recent-sales")
    public List<EnergySale> recentSales() {
        return saleRepository.findTop10ByOrderBySaleIdDesc();
    }

    @GetMapping("/recent-maintenance")
    public List<Maintenance> recentMaintenance() {
        return maintenanceRepository.findTop10ByOrderByMaintenanceIdDesc();
    }
}
