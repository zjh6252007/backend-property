package com.jay.pmanage.pojo;

import lombok.Data;


@Data
public class Tenants {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer createUser;

}
