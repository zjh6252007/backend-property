package com.jay.pmanage.service;

import com.jay.pmanage.pojo.RepairRequest;

import java.util.List;

public interface RepairRequestService {
    RepairRequest createRepairRequest(Integer propertyId,Integer tenantId,String description, String status );
    List<RepairRequest> getAll();
}
