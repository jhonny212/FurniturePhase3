package com.furniture.adminService.Controller;

import com.furniture.adminService.Model.AssignPlanPiece;
import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Model.PlanData;
import com.furniture.adminService.ServiceImp.AssignPlanPieceServiceImp;
import com.furniture.adminService.ServiceImp.PlanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/plan")
public class PlanController {

    @Autowired
    private PlanServiceImp planServiceImp;
    @Autowired
    private AssignPlanPieceServiceImp assignPlanPieceServiceImp;

    @PostMapping("")
    public ResponseEntity<Boolean> createPlan(@RequestBody PlanData planData){
        return this.planServiceImp.createPlan(planData);
    }

    @GetMapping("")
    public ResponseEntity<Page<Plan>> getAllPlans(@RequestParam Optional<Integer> page, @RequestParam Optional<String> name){
        return this.planServiceImp.getAllPlans(name, page);
    }

    @GetMapping("/get-pla-id/{id}")
    public List<AssignPlanPiece> getPlan(@PathVariable("id") int id){
        return assignPlanPieceServiceImp.getPlan(id);
    }
}
