package com.furniture.saleService.Repository;

import com.furniture.saleService.Model.Furniture;
import com.furniture.saleService.Model.FurnitureInBill;
import com.furniture.saleService.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FurnitureInBillRepository extends JpaRepository<FurnitureInBill, Integer> {

    public FurnitureInBill findByFurnitureAndProfile(Integer code, Profile profile);
    public List<FurnitureInBill> findByProfile(Profile profile);
    @Transactional
    public void deleteFurnitureInBillByProfile(Profile profile);

}
