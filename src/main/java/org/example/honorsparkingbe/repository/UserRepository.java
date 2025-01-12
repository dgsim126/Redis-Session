package org.example.honorsparkingbe.repository;

import org.example.honorsparkingbe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByOauthProviderAndOauthUserId(String oauthProvider, String oauthUserId);
}