package com.pangtaek.chatplatform.domain.auth.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangtaek.chatplatform.common.exception.CustomException;
import com.pangtaek.chatplatform.common.exception.ErrorCode;
import com.pangtaek.chatplatform.domain.auth.model.request.CreateUserRequest;
import com.pangtaek.chatplatform.domain.auth.model.request.LoginRequest;
import com.pangtaek.chatplatform.domain.auth.model.response.CreateUserResponse;
import com.pangtaek.chatplatform.domain.auth.model.response.LoginResponse;
import com.pangtaek.chatplatform.domain.repository.UserRespository;
import com.pangtaek.chatplatform.domain.repository.entity.User;
import com.pangtaek.chatplatform.domain.repository.entity.UserCredentials;
import com.pangtaek.chatplatform.security.Hasher;
import com.pangtaek.chatplatform.security.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRespository userRespository;
    private final Hasher hasher;

    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user = userRespository.findByName(request.name());
        User savedUser;

        if (user.isPresent()) {
            log.error("USER_ALREADY_EXISTS: {}", request.name());
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        try {
            User newUser = this.newUser(request.name());
            UserCredentials newCredentials = this.newUserCredentials(request.password(), newUser);
            newUser.setCredentials(newCredentials);

            savedUser = userRespository.save(newUser);

        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_SAVED_FAILED);
        }

        return new CreateUserResponse(savedUser.getName());
    }

    private User newUser(String name) {
        User newUser = User.builder()
                .name(name)
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();

        return newUser;
    }

    private UserCredentials newUserCredentials(String password, User user) {
        String hashedValue = hasher.getHashingValue(password);
        UserCredentials cre = UserCredentials.builder()
                .user(user)
                .hashed_password(hashedValue)
                .build();

        return cre;
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRespository.findByName(request.name());

        if (!user.isPresent()) {
            log.error("NOT_EXIST_USER: {}", request.name());
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }

        user.map(u -> {
            String hashedValue = hasher.getHashingValue(request.password());

            if (!u.getUserCredentials().getHashed_password().equals(hashedValue)) {
                throw new CustomException(ErrorCode.MISS_MATCH_PASSWORD);
            }

            return hashedValue;
        }).orElseThrow(() -> {
            throw new CustomException(ErrorCode.MISS_MATCH_PASSWORD);
        });

        String token = JWTProvider.createToken(request.name());

        return new LoginResponse(ErrorCode.SUCCESS, token);
    }

    public String getUserFromToken(String token) {
        return JWTProvider.getUserFromToken(token);
    }
}
