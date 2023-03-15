package com.experis_connect.models.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostDTO {
    private int id;
    private String createdAt;
    private String updatedAt;
    private String title;
    private String content;
    private String postTarget;
    private String senderId;
    private int replyParentId;
    private Set<Integer> replies;
    private String targetUser;
    private int targetGroup;
    private int targetTopic;
}
