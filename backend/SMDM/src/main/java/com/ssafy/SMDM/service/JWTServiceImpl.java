package com.ssafy.SMDM.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Builder
@Service
public class JWTServiceImpl implements JWTService{
    private String secretKey;
    private long validityInMilliseconds;

    public void JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }
    @Override
    public <T> String create(String key, T data, String subject){
        Claims claims = Jwts.claims().setSubject(subject);
        /*
        JWT 키 지정
        ->signwith : HS256 알고리즘 사용 , 비밀키는 "secretKey"
         */
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setSubject(subject)
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return jwt;
    }

}
