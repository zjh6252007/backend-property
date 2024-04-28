package com.jay.pmanage.pojo;

import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDate;

@Data
public class Contract {
    private Integer id;
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private Integer createUser;
    private Integer propertyId;
}
