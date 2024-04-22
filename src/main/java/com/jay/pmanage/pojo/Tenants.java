package com.jay.pmanage.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class Tenants {
    private String username;
    private String password;
    @JsonIgnore
    private String salt;
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer createUser;
    private boolean active;
    private String invitationToken;
}
