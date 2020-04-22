package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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
    private List<Ban> ban;
}
