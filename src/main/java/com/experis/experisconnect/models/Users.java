package com.experis.experisconnect.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;

@Entity
@Getter
@Setter
public class Users {
    @Id
    private String id;
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime updatedAt;
    @Column
    private String name;
    @Column
    private String picture;
    @Column
    private String status;
    @Column
    private String bio;
    @Column(name = "fun_fact")
    private String funFact;
    @ManyToMany(mappedBy = "users")
    private Set<Groups> groups;
    @ManyToMany(mappedBy = "users")
    private Set<Topic> topics;

    // This is the users "DM" posts.
    @OneToMany(mappedBy = "targetUser")
    private Set<Post> posts;

    // This is the users own posts
    @OneToMany(mappedBy = "senderId")
    private Set<Post> posted;

}
