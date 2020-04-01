package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String txt;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
