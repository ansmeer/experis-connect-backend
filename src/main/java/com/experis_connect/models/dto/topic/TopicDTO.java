package com.experis_connect.models.dto.topic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicDTO {
    private int id;
    private String created_at;
    private String updated_at;
    private String name;
    private String description;
    private String color;
}
