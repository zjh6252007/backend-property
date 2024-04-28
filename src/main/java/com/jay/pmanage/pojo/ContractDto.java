package com.jay.pmanage.pojo;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
public class ContractDto {
    private MultipartFile file;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    private Integer propertyId;
}
