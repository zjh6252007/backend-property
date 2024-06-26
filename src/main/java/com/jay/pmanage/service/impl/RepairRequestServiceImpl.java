package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.RepairRequestMapper;
import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.*;
import com.jay.pmanage.service.NotificationService;
import com.jay.pmanage.service.RepairRequestService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    private final RepairRequestMapper repairRequestMapper;
    private final UserMapper userMapper;
    private final TenantsMapper tenantsMapper;
    private final NotificationService notificationService;
    RepairRequestServiceImpl(RepairRequestMapper repairRequestMapper,UserMapper userMapper,TenantsMapper tenantsMapper,NotificationService notificationService){
        this.repairRequestMapper = repairRequestMapper;
        this.userMapper = userMapper;
        this.tenantsMapper = tenantsMapper;
        this.notificationService = notificationService;
    }
    @Override
    public RepairRequest createRepairRequest(String description, String available) {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userId = (Integer) userMap.get("id");
        Integer tenantId = userMapper.getTenantId(userId);
        Integer propertyId = tenantsMapper.getPropertyById(tenantId);

        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setTenantId(tenantId);
        repairRequest.setPropertyId(propertyId);
        repairRequest.setDescription(description);
        repairRequest.setStatus("Open");
        repairRequest.setAvailable(available);
        repairRequest.setCreatedAt(LocalDateTime.now());
        repairRequest.setUpdatedAt(LocalDateTime.now());
        repairRequestMapper.create(repairRequest);
        notificationService.notifyOwners("New repair request submitted!");
        return repairRequest;
    }

    @Override
    public List<RepairRequestDto> getAll() {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userid = (Integer) userMap.get("id");
        return repairRequestMapper.getAll(userid);
    }

    @Override
    public List<RepairRequest> getRepairRequestByTenantId() {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userId = (Integer) userMap.get("id");
        Integer tenantId = userMapper.getTenantId(userId); //use the id of the account to get tenant id
        return repairRequestMapper.getRepirBytenantId(tenantId);
    }

    @Override
    public void updateStatus(String status, Integer id) {
        repairRequestMapper.updateStatus(status,id);
    }
}
