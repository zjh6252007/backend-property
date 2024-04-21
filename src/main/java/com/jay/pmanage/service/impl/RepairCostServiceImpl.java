package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.RepairCostMapper;
import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.RepairCost;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.RepairCostService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairCostServiceImpl implements RepairCostService {
    private final RepairCostMapper repairCostMapper;
    private final UserMapper userMapper;
    RepairCostServiceImpl(RepairCostMapper repairCostMapper,UserMapper userMapper){
        this.repairCostMapper = repairCostMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void addRepairCost(RepairCost repairCost) {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        String username =(String) userMap.get("username");
        User user = userMapper.findUserByName(username);
        repairCost.setCreateUser(user.getId());
        repairCostMapper.add(repairCost);
    }

    @Override
    public List<RepairCost> getByPropertyId(Integer propertyId) {
        return repairCostMapper.getByPropertyId(propertyId);
    }

    @Override
    public List<RepairCost> getAll() {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userid =(Integer) userMap.get("id");
        return repairCostMapper.getAll(userid);
    }

    @Override
    public void deleteRepairCost(Integer costId) {
        repairCostMapper.delete(costId);
    }
}
