//package com.ssafy.SMDM.JWT;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.SMDM.dto.User;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.naming.AuthenticationException;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilterBean {
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        //헤더에서 JWT get
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//        //valid token 확인
//        if(token != null && jwtTokenProvider.validateToken(token)){
//            //유효하면 -> 토큰 -> 유저정보
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            // Security Context에 Authentication 객체 저장
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter(request,response);
//    }
//}
