package com.furniture.furnitureService.Repository;

import com.furniture.furnitureService.Model.AssignPlanPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignPlanPieceRepository extends JpaRepository<AssignPlanPiece, Integer> {

    List<AssignPlanPiece> findAllByPlan_Id(Integer id);
    
}