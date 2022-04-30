package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.Plan;
import com.furniture.adminService.Model.PlanData;
import com.furniture.adminService.Repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanServiceImpTest {

    @Mock
    PlanRepository planRepository;
    @Mock
    AssignPlanPieceServiceImp assignPlanPieceServiceImp;
    @InjectMocks
    PlanServiceImp planServiceImp;

    @BeforeEach
    void setUp(){ MockitoAnnotations.openMocks(this); }

    @Test
    void createPlanComplete() {
        PlanData planData = new PlanData();
        Mockito.when(
                planRepository.save(Mockito.any(Plan.class))
        ).thenReturn(planData.getPlan());
        Mockito.when(
                assignPlanPieceServiceImp.createAssignments(Mockito.anyList(),Mockito.any(Plan.class))
        ).thenReturn(ResponseEntity.status(HttpStatus.OK).body(""));
        assertEquals(
                HttpStatus.OK.value(),
                planServiceImp.createPlan(planData).getStatusCodeValue()
        );
    }

    @Test
    void createPlanCompleteButAssignmentsFailed(){
        PlanData planData = new PlanData();
        Mockito.when(
                planRepository.save(Mockito.any(Plan.class))
        ).thenReturn(planData.getPlan());
        Mockito.when(
                assignPlanPieceServiceImp.createAssignments(Mockito.anyList(),Mockito.any(Plan.class))
        ).thenThrow(RuntimeException.class);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                planServiceImp.createPlan(planData).getStatusCodeValue()
        );
    }

    @Test
    void createPlanFailed(){
        PlanData planData = new PlanData();
        Mockito.when(
                planRepository.save(Mockito.any(Plan.class))
        ).thenThrow(RuntimeException.class);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                planServiceImp.createPlan(planData).getStatusCodeValue()
        );
    }

    @Test
    void getPageOfPlans(){
        Page<Plan> list = Page.empty();
        Mockito.when(
                this.planRepository.findByNameContainsIgnoreCase(
                        Mockito.anyString(), Mockito.any(Pageable.class)
                )
        ).thenReturn(list);
        Optional<String> name = Optional.empty();
        Optional<Integer> page = Optional.empty();
        assertEquals(
                ResponseEntity.status(HttpStatus.OK).body(list),
                this.planServiceImp.getAllPlans(name, page)
        );
    }
}
