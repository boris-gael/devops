package com.test.devops.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bills")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bill")
    private List<Product> products;

    @Column(unique = true)
    private String name;

}
