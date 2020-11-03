package com.ssafy.SMDM.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.SMDM.dto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public AuthenticationFilter(AuthenticationManager authenticationManager){
        super.setAuthenticationManager(authenticationManager);
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
//            AuthenticationException{
//        UsernamePasswordAuthenticationToken authRequest;
//        try{
//            User user = new ObjectMapper().readValue(request.getInputStream(),User.class);
//            authRequest = new UsernamePasswordAuthenticationToken(user.getUserid(),user.getUserpw());
//        }catch (IOException e){
//            throw new InputNotFoundException();
//        }
//        setDetails(request,authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
}
