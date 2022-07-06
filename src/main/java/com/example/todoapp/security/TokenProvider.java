package com.example.todoapp.security;

import com.example.todoapp.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";


    public static String generateAccessToken(final User user) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + 1000 * 60 * 30L);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(user.getUserId()) // 사용자
                .setIssuedAt(new Date()) // 현재 시간 기반으로 생성
                .setExpiration(expiryDate) // 만료 시간 세팅
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact();
    }

    public String validateAndGetUserId(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)  // Base64로 디코딩 및 파싱
                .getBody();

        return claims.getSubject();
    }
}
