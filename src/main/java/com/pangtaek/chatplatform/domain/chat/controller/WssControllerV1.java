package com.pangtaek.chatplatform.domain.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.pangtaek.chatplatform.domain.chat.model.Message;
import com.pangtaek.chatplatform.domain.chat.service.ChatServieV1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WssControllerV1 {

    private final ChatServieV1 chatService;

    @MessageMapping("/chat/message/{from}")
    @SendTo("/sub/chat")
    public Message receivedMessage(@DestinationVariable String from, Message msg) {
        log.info("Message received -> from: {}, to:{}, msg:{}", from, msg.getTo(), msg.getMessage());
        chatService.saveChatMessage(msg);
    
        return msg;
    }
}
