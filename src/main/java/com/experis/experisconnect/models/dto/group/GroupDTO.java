package com.experis.experisconnect.models.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {
    private int id;
    private java.time.ZonedDateTime createdAt;
    private java.time.ZonedDateTime updatedAt;
    private String name;
    private String description;
    private String color;
    private boolean isPrivate;
}
