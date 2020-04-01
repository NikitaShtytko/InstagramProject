package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @ManyToOne()
    @JoinColumn(name="role_id", nullable=false)
    private Roles role;

    @OneToMany()
    @JoinColumn(name = "ban_id", nullable = false)
    private List<Bans> ban;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private List<Posts> post;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Comments> comment;

}
