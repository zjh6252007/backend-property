package com.jay.pmanage.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairRequestDto {
    private String id;
    private String description;
    private String available;
    private String tenantName;
    private String propertyAddress;
    private String tenantPhone;
    private String status;
    private LocalDateTime updatedAt;
}
