package com.pangtaek.chatplatform.domain.chat.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangtaek.chatplatform.domain.chat.model.Message;
import com.pangtaek.chatplatform.domain.chat.model.response.ChatListResponse;
import com.pangtaek.chatplatform.domain.repository.ChatRepository;
import com.pangtaek.chatplatform.domain.repository.entity.Chat;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServieV1 {

    private final ChatRepository chatRepository;

    public ChatListResponse chatList(String from, String to) {
        List<Chat> chats = chatRepository.findTop10BySenderOrReceiverOrderByTIDDesc(from, to);

        // Entity -> DTO
        List<Message> res = chats.stream()
                .map(chat -> new Message(chat.getReceiver(), chat.getSender(), chat.getMessage()))
                .collect(Collectors.toList());

        return new ChatListResponse(res);
    }

    @Transactional(transactionManager = "createChatTransactionManager")
    public void saveChatMessage(Message msg) {
        Chat chat = Chat.builder()
                .sender(msg.getFrom())
                .receiver(msg.getTo())
                .message(msg.getMessage())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();

        chatRepository.save(chat);
    }
}
