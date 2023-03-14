package com.experis_connect.models.dto.group;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupDTO {
    private int id;
    private String created_at;
    private String updated_at;
    private String name;
    private String description;
    private String color;
    private boolean is_private;
}
