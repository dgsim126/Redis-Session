package org.example.honorsparkingbe.service;

import org.example.honorsparkingbe.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final UserService userService;

    @Autowired
    public OAuthService(UserService userService) {
        this.userService = userService;
    }

    public UserEntity processOAuthUser(String oauthProvider, String oauthUserId, String nickname) {
        return userService.saveOrGetOAuthUser(oauthProvider, oauthUserId, nickname);
    }
}
