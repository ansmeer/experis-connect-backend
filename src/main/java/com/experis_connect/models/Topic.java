package com.experis_connect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="created_at")
    private String createdAt;
    @Column(name="updated_at")
    private String updatedAt;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String color;
    @ManyToMany
    @JoinTable(
            name = "topic_user",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")}
    )
    private Set<Users> users;
    @OneToMany(mappedBy = "targetTopic")
    private Set<Post> posts;
}
