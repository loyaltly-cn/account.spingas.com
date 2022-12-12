package com.spingas.account.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String icon;
    private String phone;
    private String pwd;
    private Boolean subscription;

    public User(){}

    public User(Integer id){
        this.id = id;
    }
}
