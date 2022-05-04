package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.AssignPlanPiece;
import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Repository.AssignPlanPieceRepository;
import com.furniture.adminService.Service.AssignPlanPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AssignPlanPieceServiceImp implements AssignPlanPieceService {

    @Autowired
    private AssignPlanPieceRepository assignPlanPieceRepository;

    @Override
    public ResponseEntity<Boolean> createAssignments(List<AssignPlanPiece> assignments, Plan plan) {
        for (AssignPlanPiece assignment : assignments) {
            assignment.setPlan(plan);
            this.assignPlanPieceRepository.save(assignment);
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    public List<AssignPlanPiece> getPlan(int id){
        return assignPlanPieceRepository.findAllByPlan_Id(id);
    }
}
