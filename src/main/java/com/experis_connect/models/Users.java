package com.experis_connect.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;




import jakarta.persistence.*;

@Entity
    @Setter
    @Getter
    public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column
        private String created_at;
        @Column
        private String updated_at;
        @Column
        private String user_name;
        @Column
        private String picture;
        @Column
        private String status;
        @Column
        private String bio;
        @Column
        private String fun_fact;


}
