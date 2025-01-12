package org.example.honorsparkingbe.controller;

import org.example.honorsparkingbe.domain.UserEntity;
import org.example.honorsparkingbe.service.KakaoLoginService;
import org.example.honorsparkingbe.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final OAuthService oauthService;

    @Autowired
    public KakaoLoginController(KakaoLoginService kakaoLoginService, OAuthService oauthService) {
        this.kakaoLoginService = kakaoLoginService;
        this.oauthService = oauthService;
    }

    @GetMapping("kakaoLogin")
    public ResponseEntity<Void> redirectToKakaoLogin() {
        return ResponseEntity.status(302)
                .header("Location", "https://kauth.kakao.com/oauth/authorize?client_id=ac5b41063a0eee50117cbb02cdbd0fb9&redirect_uri=http://127.0.0.1:8080/api/v1/login/kakao&response_type=code")
                .build();
    }


    @GetMapping("kakao")
    public ResponseEntity<UserEntity> kakaoLogin(@RequestParam String code) {
        // 1. 인가 코드로 Access Token 요청
        String accessToken = kakaoLoginService.getAccessToken(code);

        // 2. Access Token으로 사용자 정보 요청
        Map<String, Object> userInfo = kakaoLoginService.getUserInfoAsMap(accessToken);

        // 3. 사용자 정보 파싱
        String oauthUserId = String.valueOf(userInfo.get("id")); // 카카오 고유 ID
        Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
        String nickname = (String) properties.get("nickname");

        // 4. 사용자 정보 저장 또는 조회
        UserEntity user = oauthService.processOAuthUser("KAKAO", oauthUserId, nickname);

        // 5. 저장된 사용자 정보 반환
        return ResponseEntity.ok(user);
    }
}
