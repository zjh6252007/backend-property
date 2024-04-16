package com.jay.pmanage.pojo;

import lombok.Data;

@Data
public class RepairRequestDto {
    private int propertyId;
    private int tenantId;
    private String description;
    private String status;
}
