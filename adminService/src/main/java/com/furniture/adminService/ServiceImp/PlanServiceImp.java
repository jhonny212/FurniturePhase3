package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.PlanData;
import com.furniture.adminService.Repository.PlanRepository;
import com.furniture.adminService.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImp implements PlanService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private AssignPlanPieceServiceImp assignPlanPieceServiceImp;

    @Override
    public ResponseEntity<String> createPlan(PlanData planData) {
        try{
            this.planRepository.save(planData.getPlan());
            return this.assignPlanPieceServiceImp.createAssignments(planData.getAssignments(), planData.getPlan());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error al crear el plan");
        }
    }

}
