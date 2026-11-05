package com.filipe.estudo_spring.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filipe.estudo_spring.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
