package com.experis_connect.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Users {
    @Id
    private String id;
    @Column
    private String created_at;
    @Column
    private String updated_at;
    @Column
    private String name;
    @Column
    private String picture;
    @Column
    private String status;
    @Column
    private String bio;
    @Column
    private String fun_fact;
    @ManyToMany(mappedBy = "users")
    private Set<Groups> groups;
    @ManyToMany(mappedBy = "users")
    private Set<Topic> topics;

    // This is the users "DM" posts.
    @OneToMany(mappedBy = "target_user")
    private Set<Post> posts;

    // This is the users own posts
    @OneToMany(mappedBy = "sender_id")
    private Set<Post> posted;

}
