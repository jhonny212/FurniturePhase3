package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Model.PlanData;
import com.furniture.adminService.Repository.PlanRepository;
import com.furniture.adminService.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlanServiceImp implements PlanService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private AssignPlanPieceServiceImp assignPlanPieceServiceImp;

    @Override
    @Transactional
    public ResponseEntity<Boolean> createPlan(PlanData planData) {
        try{
            this.planRepository.save(planData.getPlan());
            return this.assignPlanPieceServiceImp.createAssignments(planData.getAssignments(), planData.getPlan());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @Override
    public ResponseEntity<Page<Plan>> getAllPlans(Optional<String> name, Optional<Integer> page){
        return ResponseEntity.status(HttpStatus.OK).body(this.planRepository.findByNameContainsIgnoreCase(
                name.orElse(""),
                PageRequest.of(
                        page.orElse(0),5
                )
        ));
    }

}
