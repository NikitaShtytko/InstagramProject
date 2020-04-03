package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(name = "post_id", insertable = false, updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "post_id",insertable = false, updatable = false)
    private Post post;
}
