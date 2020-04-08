package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Ban{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_id")
    private Long id;

    private String txt;

    private Timestamp date;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
//    private User user;
}
