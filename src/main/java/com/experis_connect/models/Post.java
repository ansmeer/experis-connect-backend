package com.experis_connect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Column
    private String sender_id;
    @Column
    private int reply_parent_id;
    @Column
    private String target_user;
    @Column
    private int target_group;
    @Column
    private int target_topic;

}
