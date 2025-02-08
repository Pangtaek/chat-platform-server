package com.pangtaek.chatplatform.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pangtaek.chatplatform.domain.repository.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findTop10BySenderOrReceiverOrderByTIDDesc(String sender, String receiver);

    // @Query("SELECT c " +
    //         "FROM chat AS c " +
    //         "WHERE  c.sender = :sender " +
    //         "OR c.receiver=:receiver " +
    //         "ORDER BY c.t_id " +
    //         "DESC " +
    //         "LIMIT 10")
    // List<Chat> findTop10Chrts(
    //         @Param("sender") String sender,
    //         @Param("receiver") String receiver);
}
