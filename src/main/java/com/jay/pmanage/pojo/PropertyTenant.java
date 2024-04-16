package com.jay.pmanage.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PropertyTenant {
    private int propertyId;
    private int tenantId;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
}
