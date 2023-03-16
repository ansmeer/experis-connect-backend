package com.experis.experisconnect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column
    private String title;
    @Column
    private String content;
    @Column(name = "post_target")
    private String postTarget;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users senderId;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post replyParentId;
    @OneToMany(mappedBy = "replyParentId")
    private Set<Post> replies;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users targetUser;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups targetGroup;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic targetTopic;

}
