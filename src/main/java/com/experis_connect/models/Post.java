package com.experis_connect.models;

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
    @Column
    private String created_at;
    @Column
    private String updated_at;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String post_target;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users sender_id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post reply_parent_id;

    @OneToMany(mappedBy = "reply_parent_id")
    private Set<Post> replies;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users target_user;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups target_group;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic target_topic;

}
