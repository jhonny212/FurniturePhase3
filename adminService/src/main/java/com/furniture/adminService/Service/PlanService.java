package com.furniture.adminService.Service;

import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Model.PlanData;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PlanService {

    public ResponseEntity<String> createPlan(PlanData planData);

    ResponseEntity<Page<Plan>> getAllPlans(Optional<String> name, Optional<Integer> page);
}
