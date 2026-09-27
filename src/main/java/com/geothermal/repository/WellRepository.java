package com.geothermal.repository;

import com.geothermal.entity.Well;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellRepository extends JpaRepository<Well, Long> {
}
