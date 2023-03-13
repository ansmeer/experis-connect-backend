package com.experis_connect.models.dto.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsersDTO {
    private String id;
    private String created_at;
    private String updated_at;
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String fun_fact;
    private Set<Integer> posts;
    private Set<Integer> posted;
}
