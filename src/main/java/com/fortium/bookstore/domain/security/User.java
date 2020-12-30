package com.fortium.bookstore.domain.security;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String roles;
}