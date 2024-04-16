package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.RepairRequestMapper;
import com.jay.pmanage.pojo.RepairRequest;
import com.jay.pmanage.service.RepairRequestService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    private final RepairRequestMapper repairRequestMapper;
    RepairRequestServiceImpl(RepairRequestMapper repairRequestMapper){
        this.repairRequestMapper = repairRequestMapper;
    }
    @Override
    public RepairRequest createRepairRequest(Integer propertyId, Integer tenantId, String description, String status) {
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setPropertyId(propertyId);
        repairRequest.setTenantId(tenantId);
        repairRequest.setDescription(description);
        repairRequest.setStatus(status);
        repairRequest.setCreatedAt(LocalDateTime.now());
        repairRequest.setUpdatedAt(LocalDateTime.now());
        repairRequestMapper.create(repairRequest);
        return repairRequest;
    }

    @Override
    public List<RepairRequest> getAll() {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userid = (Integer) userMap.get("id");
        return repairRequestMapper.getAll(userid);
    }
}
