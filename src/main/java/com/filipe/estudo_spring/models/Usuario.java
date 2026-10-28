package com.filipe.estudo_spring.models;

import jakarta.persistence.*;

@Entity 
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(unique = true)
    private String login;

    @Column(name="is_ativo")
    private Boolean isAtivo = true;
}
