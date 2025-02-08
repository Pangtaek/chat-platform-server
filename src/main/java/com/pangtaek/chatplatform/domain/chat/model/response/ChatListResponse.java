package com.pangtaek.chatplatform.domain.chat.model.response;

import java.util.List;

import com.pangtaek.chatplatform.domain.chat.model.Message;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Chatting List")
public record ChatListResponse(
        @Schema(description = "Message []") List<Message> result) {
}
