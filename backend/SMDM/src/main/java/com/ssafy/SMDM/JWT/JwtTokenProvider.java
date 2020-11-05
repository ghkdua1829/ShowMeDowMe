//package com.ssafy.SMDM.JWT;
//
//import com.ssafy.SMDM.dto.User;
//import com.ssafy.SMDM.service.UserService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Component
//public class JwtTokenProvider {
//    private String secretKey = "ShowmeDowme";
//
//    //토큰 유효시간
//    private long tokenValidTime = 30 * 60 * 1000L;
//
//    private final UserService userService;
//
//    //SecretKey to Base64 incoding
//    @PostConstruct
//    protected void init(){
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    //JWT 토큰 생성
//    public String createToken(String userPk, List<String> roles){
//        //JWT payload 에 저장되는 정보
//        Claims claims = Jwts.claims().setSubject(userPk);
//        claims.put("roles",roles);// key/value로 저장
//        Date now = new Date();
//        return Jwts.builder()
//                .setClaims(claims) // 정보저장
//                .setIssuedAt(now) //발행정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) //expire
//                .signWith(SignatureAlgorithm.HS256,secretKey) //알고리즘 + secretkey
//                .compact();
//    }
//
//    //인증 정보 조회
//    public Authentication getAuthentication(String token){
//        Optional<User> user = userService.findByUserId(this.getUserPk(token));
//        return new UsernamePasswordAuthenticationToken(user.get(),"",user.get().getAuthorities());
//    }
//
//    //토큰에서 회원 정보 추출
//    public String getUserPk(String token){
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    //Request의 Header에서 token 값을 가져옴. "token" : "token값"
//    public String resolveToken(HttpServletRequest request){
//        return request.getHeader("X-AUTH-TOKEN");
//    }
//
//    //토큰 유효성 + 만료일자 확인
//    public boolean validateToken(String jwtToken){
//        try{
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//            return !claims.getBody().getExpiration().before(new Date());
//        }catch (Exception e){
//            return false;
//        }
//    }
//}
