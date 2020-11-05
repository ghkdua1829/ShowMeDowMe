//package com.ssafy.SMDM.config;
//
//import com.ssafy.SMDM.JWT.JwtAuthenticationFilter;
//import com.ssafy.SMDM.JWT.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtTokenProvider jwtTokenProvider;
////    패스워드 암호화할때 쓰는거!
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    //authenticationManager bean 등록
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
//
//
//    //css ,js ,img 등 정적자원에 대해서는 로그인 무시
//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
//    }
//
//    //http security 설정 -> csrf : 토큰 활용시 모든요청에 대해 접근가능
//    //session:  JWT를 이용할 것이므로 사용 X 설정
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**").hasRole("USER")
//                .antMatchers("/**").permitAll()
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
//        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
//    }
//
//
//}
