package com.furniture.adminService.Service;

import com.furniture.adminService.Model.AssignPlanPiece;
import com.furniture.adminService.Model.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignPlanPieceService {

    public ResponseEntity<String> createAssignments(List<AssignPlanPiece> assignments, Plan plan) throws Exception;
}
