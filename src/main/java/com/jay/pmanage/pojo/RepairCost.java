package com.jay.pmanage.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RepairCost {
    private Integer id;
    private Integer createUser;
    private Integer propertyId;
    private String description;
    private BigDecimal cost;
    private LocalDateTime date;
}
