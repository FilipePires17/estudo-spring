package com.filipe.estudo_spring.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column(unique = true)
    @NotNull 
    private String email;
    
    @Column(unique = true)
    @NotNull 
    private String cpf;
    
    @Column(unique = true)
    @NotNull 
    private String login;
    
    @Builder.Default
    @Column(name="is_active")
    @NotNull 
    private Boolean isActive = true;

    @Builder.Default
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate 
    void preUpdate() {
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }
}
