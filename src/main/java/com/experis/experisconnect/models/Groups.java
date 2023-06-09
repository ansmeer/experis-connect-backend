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
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.time.ZonedDateTime updatedAt;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String color;
    @Column(name="is_private")
    private boolean isPrivate;
    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")}
    )
    private Set<Users> users;
    @OneToMany(mappedBy = "targetGroup")
    private Set<Post> posts;
}
