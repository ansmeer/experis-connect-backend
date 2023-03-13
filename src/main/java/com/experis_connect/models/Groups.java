package com.experis_connect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String created_at;
    @Column
    private String updated_at;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String color;
    @Column
    private boolean is_private;
    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")}
    )
    private Set<Users> users;
    @OneToMany(mappedBy = "target_group")
    private Set<Post> posts;
}
