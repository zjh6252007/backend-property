package com.jay.pmanage.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RepairRequest {
    private int id;
    private int propertyId;
    private int tenantId;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
