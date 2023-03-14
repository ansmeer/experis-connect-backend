package com.experis_connect.models.dto.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersPutDTO {
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String funFact;
}
