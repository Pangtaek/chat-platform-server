package com.pangtaek.chatplatform.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangtaek.chatplatform.domain.auth.model.request.CreateUserRequest;
import com.pangtaek.chatplatform.domain.auth.model.response.CreateUserResponse;
import com.pangtaek.chatplatform.domain.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/* 
 * 클래스명에 버전을 붙여야 하는 이유
 * 서비스를 동작시키는 과정에서 플랫폼에 대한 서버와 프론트도 업데이트도 되어야 한다.
 * 위 과정에서는 서버가 프론트보다 먼저 배포된다. 하지만 기존 API에 대하여 서버가 먼저 변경되어 배포되면, 
 * 올바르지 않거나 원하는 데이터가 전달되지 않아 오류가 발생하는 것을 예방할 수 있다.
 */

@Tag(name = "Auth API", description = "V1 Auth API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

    private final AuthService authService;

    @Operation(summary = "새로운 유저를 생성합니다.", description = "새로운 유저 생성")
    @PostMapping(value = "/create-user")
    public CreateUserResponse createUser(
            @RequestBody @Valid CreateUserRequest request) {
        return authService.createUser(request);
    }
}
