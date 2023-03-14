package com.experis_connect.models.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPutDTO {
    private String name;
    private String description;
    private String color;
    private boolean is_private;
}
