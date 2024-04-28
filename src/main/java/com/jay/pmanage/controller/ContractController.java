package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Contract;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;
    ContractController(ContractService contractService){
        this.contractService = contractService;
    }
    @GetMapping("/{id}")
    public Result<List<Contract>> getContractByPropertyId(@PathVariable Integer id)
    {
        return Result.success(contractService.getContractByPropertyId(id));
    }
}
