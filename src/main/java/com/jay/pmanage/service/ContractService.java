package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Contract;

import java.util.List;

public interface ContractService {
    void createContract(Contract contract);
    List<Contract> getContractByPropertyId(Integer id);
}
