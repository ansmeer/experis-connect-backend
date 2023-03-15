package com.experis_connect.models.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private int id;
    private String createdAt;
    private String updatedAt;
    private String title;
    private String content;
    private String postTarget;
    private int senderId;
    private int replyParentId;
    private String targetUser;
    private int targetGroup;
    private int targetTopic;
}
