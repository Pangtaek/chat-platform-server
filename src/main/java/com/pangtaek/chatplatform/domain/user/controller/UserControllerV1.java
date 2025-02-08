package com.pangtaek.chatplatform.domain.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangtaek.chatplatform.domain.user.model.response.UserSearchResponse;
import com.pangtaek.chatplatform.domain.user.service.UserServiceV1;
import com.pangtaek.chatplatform.security.JWTProvider;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User API", description = "V1 User API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserServiceV1 userService;

    public UserSearchResponse searchUser(
            @PathVariable("name") String name,
            @RequestHeader("Authorization") String authString) {

        // ex: Bearer s123aefsd
        String token = JWTProvider.extractToken(authString);
        String user = JWTProvider.getUserFromToken(token);

        return userService.searchUser(name, user);
    }
}
