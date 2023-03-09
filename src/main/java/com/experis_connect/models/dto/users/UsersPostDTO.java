package com.experis_connect.models.dto.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersPostDTO {
    private int id;
    private String created_at;
    private String updated_at;
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String fun_fact;
}
