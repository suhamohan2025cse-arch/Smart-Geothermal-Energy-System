package com.geothermal.repository;

import com.geothermal.entity.EnergySale;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnergySaleRepository extends JpaRepository<EnergySale, Long> {
    List<EnergySale> findTop10ByOrderBySaleIdDesc();
}
