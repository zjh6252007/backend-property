package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.RepairRequestMapper;
import com.jay.pmanage.pojo.RepairRequest;
import com.jay.pmanage.service.RepairRequestService;
import org.springframework.stereotype.Service;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    private final RepairRequestMapper repairRequestMapper;
    RepairRequestServiceImpl(RepairRequestMapper repairRequestMapper){
        this.repairRequestMapper = repairRequestMapper;
    }
    @Override
    public void createRepairRequest(RepairRequest repairRequest) {
        repairRequestMapper.create(repairRequest);
    }
}
