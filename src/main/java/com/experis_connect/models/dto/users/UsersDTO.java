package com.experis_connect.models.dto.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsersDTO {
    private String id;
    private String createdAt;
    private String updatedAt;
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String funFact;
    private Set<Integer> groups;
    private Set<Integer> topics;
}
