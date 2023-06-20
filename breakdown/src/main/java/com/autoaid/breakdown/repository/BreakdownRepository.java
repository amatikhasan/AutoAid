package com.autoaid.breakdown.repository;

import com.autoaid.breakdown.model.Breakdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreakdownRepository extends JpaRepository<Breakdown, Long>{
    List<Breakdown> findByUserId(int userId);
}
