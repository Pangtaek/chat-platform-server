package com.pangtaek.chatplatform.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pangtaek.chatplatform.domain.repository.entity.User;

public interface UserRespository extends JpaRepository<User, Long> {
    
    Optional<User> findByName(String name);

    boolean exexistsByName(String name);
}