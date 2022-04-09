package com.furniture.inventoryService.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assign_plan_piece")
public class AssignPlanPiece implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plan",nullable = false)
    private Plan plan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_piece",nullable=false)
    private Piece piece;
    @Column(nullable=false)
    private Integer amount;

    public AssignPlanPiece(Integer id, Plan plan, Piece piece, Integer amount) {
        this.id = id;
        this.plan = plan;
        this.piece = piece;
        this.amount = amount;
    }

    public AssignPlanPiece(){}

    @Override
    public String toString() {
        return "AssignPlanPiece{" +
                "id=" + id +
                ", plan=" + plan +
                ", piece=" + piece +
                ", amount=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}