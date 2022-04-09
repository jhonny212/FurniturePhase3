package com.furniture.reportService.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BillData implements Serializable {
    private Bill bill;
    private ArrayList<BillDetails> details;

    public BillData(){}

    public BillData(Bill bill, ArrayList<BillDetails> details) {
        this.bill = bill;
        this.details = details;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public ArrayList<BillDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<BillDetails> details) {
        this.details = details;
    }
}
