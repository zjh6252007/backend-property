package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.ContractMapper;
import com.jay.pmanage.pojo.Contract;
import com.jay.pmanage.service.ContractService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractMapper contractMapper;
    ContractServiceImpl(ContractMapper contractMapper)
    {
        this.contractMapper = contractMapper;
    }


    @Override
    public void createContract(Contract contract) {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userid = (Integer) userMap.get("id");
        contract.setCreateUser(userid);
        contractMapper.insertContract(contract);
    }

    @Override
    public List<Contract> getContractByPropertyId(Integer id) {
        return contractMapper.findContractByPropertyId(id);
    }
}
