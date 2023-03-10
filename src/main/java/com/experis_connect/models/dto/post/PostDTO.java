package com.experis_connect.models.dto.post;

import com.experis_connect.models.PostTargetType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private int id;
    private String created_at;
    private String updated_at;
    private String title;
    private String content;
    private PostTargetType post_target;
    private String sender_id;
    private int reply_parent_id;
    private String target_user;
    private int target_group;
    private int target_topic;
}
