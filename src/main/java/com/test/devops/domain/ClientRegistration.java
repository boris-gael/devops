package com.test.devops.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
public class ClientRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(unique = true, nullable = false)
    private String clientId;

    @Column(unique = true, nullable = false)
    private String clientSecret;

}
