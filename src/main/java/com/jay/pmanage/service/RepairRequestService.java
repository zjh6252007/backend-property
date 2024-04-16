package com.jay.pmanage.service;

import com.jay.pmanage.pojo.RepairRequest;

public interface RepairRequestService {
    RepairRequest createRepairRequest(Integer propertyId,Integer tenantId,String description, String status );
}
