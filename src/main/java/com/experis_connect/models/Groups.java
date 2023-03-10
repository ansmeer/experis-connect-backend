package com.experis_connect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
