package com.pangtaek.chatplatform.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pangtaek.chatplatform.domain.repository.entity.User;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean exexistsByName(String name);

    @Query("SELECT u.name " +
            "FROM User AS u " +
            "WHERE LOCATE(LOWER(:pattern), LOWER(u.name)) > 0 " +
            "AND u.name != :user")
    List<String> findNameByNameMatch(
            @Param("pattern") String pattern,
            @Param("user") String user);
}