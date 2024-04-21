package com.jay.pmanage.service;

import com.jay.pmanage.pojo.RepairCost;

import java.util.List;

public interface RepairCostService {
    void addRepairCost(RepairCost repairCost);
    List<RepairCost> getByPropertyId(Integer propertyId);
    List<RepairCost> getAll();
    void deleteRepairCost(Integer costId);
}
