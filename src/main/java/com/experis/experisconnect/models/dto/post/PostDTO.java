package com.experis.experisconnect.models.dto.post;

import com.experis.experisconnect.models.dto.group.GroupMiniDTO;
import com.experis.experisconnect.models.dto.topic.TopicMiniDTO;
import com.experis.experisconnect.models.dto.users.SenderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostDTO {
    private int id;
    private java.time.ZonedDateTime createdAt;
    private java.time.ZonedDateTime updatedAt;
    private String title;
    private String content;
    private String postTarget;
    private SenderDTO senderId;
    private int replyParentId;
    private Set<Integer> replies;
    private String targetUser;
    private GroupMiniDTO targetGroup;
    private TopicMiniDTO targetTopic;
}
