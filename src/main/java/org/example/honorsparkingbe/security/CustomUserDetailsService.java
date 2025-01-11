package org.example.honorsparkingbe.security;

/**
 * Spring Security의 "인증" 과정을 담당
 * - 사용자 정보를 데이터베이스에서 조회하고, 조회된 사용자 정보를 Spring Security가 처리할 수 있는 형태로 반환
 */


import org.example.honorsparkingbe.domain.UserEntity;
import org.example.honorsparkingbe.security.CustomUserDetails;
import org.example.honorsparkingbe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        // 사용자 정보가 존재하면 CustomUserDetails 객체를 반환
        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
