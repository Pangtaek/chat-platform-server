package com.pangtaek.chatplatform.domain.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangtaek.chatplatform.domain.chat.model.response.ChatListResponse;
import com.pangtaek.chatplatform.domain.chat.service.ChatServieV1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Chat API", description = "V1 Chat API")
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatControllerV1 {

    private final ChatServieV1 chatServie;

    @GetMapping("/chat-list")
    @Operation(summary = "채팅 리스트를 가져옵니다.", description = "가장 최근 10개의 채팅 리스트를 가져옵니다.")
    public ChatListResponse chatList(
            @RequestParam("name") @Valid String from,
            @RequestParam("from") @Valid String to) {
                
        return chatServie.chatList(from, to);
    }
}
