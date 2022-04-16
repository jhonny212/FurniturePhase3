package com.furniture.adminService.Service;

import com.furniture.adminService.Model.PlanData;
import org.springframework.http.ResponseEntity;

public interface PlanService {

    public ResponseEntity<String> createPlan(PlanData planData);
}
