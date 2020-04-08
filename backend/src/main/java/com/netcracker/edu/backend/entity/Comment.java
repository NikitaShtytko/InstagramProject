package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String txt;

//    @ManyToOne
//    @JoinColumn(name = "post_id", insertable = false, updatable = false)
//    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}

