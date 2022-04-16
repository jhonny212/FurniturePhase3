package com.furniture.adminService.Repository;

import com.furniture.adminService.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
