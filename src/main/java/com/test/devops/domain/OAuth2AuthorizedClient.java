package com.test.devops.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "authorized_clients")
@Data
public class OAuth2AuthorizedClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ClientRegistration clientRegistration;

    private String token;

}
