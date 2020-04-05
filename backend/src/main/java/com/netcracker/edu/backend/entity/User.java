package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login")
    private String login;

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
    @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Role role;

    //todo Простестировать работу OneToMany relationship.
    @OneToMany()
    @JoinColumn(name = "ban_id", nullable = false, insertable = false, updatable = false)
    private List<Ban> ban;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private Set<Post> post;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "post_id")
//    private List<Post> post;

//    @OneToMany
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private List<Comment> comment;
}


//TODO generic