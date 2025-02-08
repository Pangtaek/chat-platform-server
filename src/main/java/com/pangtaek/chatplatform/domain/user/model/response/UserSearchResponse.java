package com.pangtaek.chatplatform.domain.user.model.response;

import java.util.List;

import com.pangtaek.chatplatform.common.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User 검색 리스트")
public record UserSearchResponse(
        @Schema(description = "error code") ErrorCode description,
        @Schema(description = "이름") List<String> name) {
}