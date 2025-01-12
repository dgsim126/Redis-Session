package org.example.honorsparkingbe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L; // 직렬화 버전 ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String role;

    // OAuth 관련 필드 추가
    private String oauthProvider; // OAuth 제공자 (예: 'KAKAO', 'GOOGLE', 'NAVER')

    private String oauthUserId; // OAuth 제공자의 고유 사용자 ID
}
