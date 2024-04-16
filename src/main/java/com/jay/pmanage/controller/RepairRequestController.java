package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.RepairRequest;
import com.jay.pmanage.pojo.RepairRequestDto;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.RepairRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repair-requests")
public class RepairRequestController {
    private final RepairRequestService repairRequestService;
    RepairRequestController(RepairRequestService repairRequestService){
        this.repairRequestService = repairRequestService;
    }

    @PostMapping("/create")
    public Result<RepairRequest> createRepairRequest(@RequestBody RepairRequestDto repairRequestDto)
    {
        RepairRequest repairRequest = repairRequestService.createRepairRequest(
                repairRequestDto.getPropertyId(),
                repairRequestDto.getTenantId(),
                repairRequestDto.getDescription(),
                repairRequestDto.getStatus()
        );
        return Result.success(repairRequest);
    }

    @GetMapping("/getAll")
    public Result <List<RepairRequest>> getAllRepairRequest(){
        if(repairRequestService.getAll() != null){
            return Result.success(repairRequestService.getAll());
        }else{
            return Result.error("No such Data");
        }
    }
}

