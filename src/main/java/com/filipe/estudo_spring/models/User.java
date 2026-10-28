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
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(unique = true, nullable = false)
    @NotNull 
    private String email;
    
    @Column(unique = true, nullable = false)
    @NotNull 
    private String cpf;
    
    @Column(unique = true, nullable = false)
    @NotNull 
    private String login;
    
    @Builder.Default
    @Column(name="is_active", nullable = false)
    @NotNull 
    private Boolean isActive = true;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    @NotNull
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate 
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
