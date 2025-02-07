package com.pangtaek.chatplatform.domain.auth.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangtaek.chatplatform.common.exception.CustomException;
import com.pangtaek.chatplatform.common.exception.ErrorCode;
import com.pangtaek.chatplatform.domain.auth.model.request.CreateUserRequest;
import com.pangtaek.chatplatform.domain.auth.model.response.CreateUserResponse;
import com.pangtaek.chatplatform.domain.repository.UserRespository;
import com.pangtaek.chatplatform.domain.repository.entity.User;
import com.pangtaek.chatplatform.domain.repository.entity.UserCredentials;
import com.pangtaek.chatplatform.security.Hasher;

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

        if (user.isPresent()) {
            log.error("USER_ALREADY_EXISTS: {}", request.name());
            throw new CustomException(ErrorCode.SUCCESS);
        }

        try {
            User newUser = this.newUser(request.name());
            UserCredentials newCredentials = this.newUserCredentials(request.password(), newUser);
            newUser.setCredentials(newCredentials);

            User savedUser = userRespository.save(newUser);

            if (savedUser == null) {
                throw new CustomException(ErrorCode.USER_SAVED_FAILED);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_SAVED_FAILED);
        }

        return new CreateUserResponse(request.name());
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
}
