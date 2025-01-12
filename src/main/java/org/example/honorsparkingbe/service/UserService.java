package org.example.honorsparkingbe.service;

import org.example.honorsparkingbe.domain.UserEntity;
import org.example.honorsparkingbe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveOrGetOAuthUser(String oauthProvider, String oauthUserId, String nickname) {
        Optional<UserEntity> existingUser = userRepository.findByOauthProviderAndOauthUserId(oauthProvider, oauthUserId);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        UserEntity newUser = new UserEntity();
        newUser.setOauthProvider(oauthProvider);
        newUser.setOauthUserId(oauthUserId);
        newUser.setUsername(nickname);
        newUser.setName(nickname);
        newUser.setRole("ROLE_USER");

        return userRepository.save(newUser);
    }
}
