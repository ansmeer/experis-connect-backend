package com.experis.experisconnect.models.dto.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsersDTO {
    private String id;
    private java.time.ZonedDateTime createdAt;
    private java.time.ZonedDateTime updatedAt;
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String funFact;
    private Set<Integer> groups;
    private Set<Integer> topics;
}
