package com.jay.pmanage.service;

import com.jay.pmanage.pojo.RepairRequest;
import com.jay.pmanage.pojo.RepairRequestDto;

import java.util.List;

public interface RepairRequestService {
    RepairRequest createRepairRequest(String description, String available );
    List<RepairRequestDto> getAll();
    List<RepairRequest> getRepairRequestByTenantId();
    void updateStatus(String status,Integer id);
}
