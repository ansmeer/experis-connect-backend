package com.experis_connect.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPutDTO {
    private int id;
    private String created_at;
    private String updated_at;
    private String title;
    private String content;
    private String post_target;
}
