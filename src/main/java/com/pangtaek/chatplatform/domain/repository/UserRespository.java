package com.pangtaek.chatplatform.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangtaek.chatplatform.domain.repository.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    
    Optional<User> findByName(String name);

    boolean exexistsByName(String name);
}