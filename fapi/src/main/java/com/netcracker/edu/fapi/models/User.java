package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Long id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
    private UserGender gender;
    private String status;
    private Timestamp created;
    private List<Ban> ban;
    private String photo;
}
