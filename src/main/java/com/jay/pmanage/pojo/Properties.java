package com.jay.pmanage.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Properties {
    private Integer id;
    private String address;
    private String state;
    private BigDecimal price;
    private Integer ownerId;
    private String propertyType;

}
