package com.experis_connect.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPostDTO {
    private String title;
    private String content;
    private String post_target;
    private String sender_id;
    private int reply_parent_id;
    private String target_user;
    private int target_group;
    private int target_topic;
}
