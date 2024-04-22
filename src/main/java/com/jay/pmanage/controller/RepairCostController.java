package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.RepairCost;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.RepairCostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost")
public class RepairCostController {
    private final RepairCostService repairCostService;
    RepairCostController(RepairCostService repairCostService){
        this.repairCostService = repairCostService;
    }
    @PostMapping("/add")
    public Result<RepairCost> add(@RequestBody RepairCost repairCost){
        repairCostService.addRepairCost(repairCost);
        return Result.success(repairCost);
    }

    @GetMapping("/getCost/{id}")
    public Result<List<RepairCost>> getByPropertyId(@PathVariable Integer id)
    {
        List<RepairCost> repairCostList = repairCostService.getByPropertyId(id);
        if(repairCostList != null){
            return Result.success(repairCostList);
        }else{
            return Result.error("No Data found");
        }
    }

    @GetMapping("/getAll")
    public Result<List<RepairCost>> getAll()
    {
        List<RepairCost> repairCostList = repairCostService.getAll();
        if(repairCostList != null){
            return Result.success(repairCostList);
        }else{
            return Result.error("No Data found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id){
        repairCostService.deleteRepairCost(id);
        return Result.success();
    }
}
