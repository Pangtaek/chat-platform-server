package com.pangtaek.chatplatform.domain.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pangtaek.chatplatform.common.exception.ErrorCode;
import com.pangtaek.chatplatform.domain.repository.UserRespository;
import com.pangtaek.chatplatform.domain.user.model.response.UserSearchResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceV1 {
    
    private final UserRespository userRespository;

    public UserSearchResponse searchUser(String name, String user) {
        List<String> names = userRespository.findNameByNameMatch(name, user);

        return new UserSearchResponse(ErrorCode.SUCCESS, names);
    }
}
