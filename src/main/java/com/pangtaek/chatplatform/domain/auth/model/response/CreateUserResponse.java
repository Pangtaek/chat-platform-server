package com.pangtaek.chatplatform.domain.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 생성 response")
public record CreateUserResponse(

        @Schema(description = "생성 유무") String code) {
}
