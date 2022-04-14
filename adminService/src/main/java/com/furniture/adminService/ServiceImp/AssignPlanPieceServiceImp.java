package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.AssignPlanPiece;
import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Repository.AssignPlanPieceRepository;
import com.furniture.adminService.Service.AssignPlanPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignPlanPieceServiceImp implements AssignPlanPieceService {

    @Autowired
    private AssignPlanPieceRepository assignPlanPieceRepository;

    @Override
    public ResponseEntity<String> createAssignments(List<AssignPlanPiece> assignments, Plan plan) {
        int i = 0;
        try{
            for (i = 0; i < assignments.size(); i++){
                assignments.get(i).setPlan(plan);
                this.assignPlanPieceRepository.save(assignments.get(i));
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha creado el plan y sus asignaciones correctamente");
        }catch(Exception ex){
            for(int j = 0; j < i; j++){
                this.assignPlanPieceRepository.delete(assignments.get(i));
            }
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Ha ocurrido un error al registrar las asignaciones");
        }
    }
}
