package com.furniture.salesReportService.Model;

import java.util.ArrayList;

public class PlanData {
    private Plan plan;
    private ArrayList<AssignPlanPiece> assignments;

    public PlanData(){}
    public PlanData(Plan plan, ArrayList<AssignPlanPiece> assignments){
        this.plan = plan;
        this.assignments = assignments;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<AssignPlanPiece> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<AssignPlanPiece> assignments) {
        this.assignments = assignments;
    }
}
