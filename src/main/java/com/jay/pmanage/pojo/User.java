package com.jay.pmanage.pojo;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime enrollDate;
}
