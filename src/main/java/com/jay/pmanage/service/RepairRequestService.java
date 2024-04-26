package com.jay.pmanage.service;

import com.jay.pmanage.pojo.RepairRequest;

import java.util.List;

public interface RepairRequestService {
    RepairRequest createRepairRequest(String description, String available );
    List<RepairRequest> getAll();
}
