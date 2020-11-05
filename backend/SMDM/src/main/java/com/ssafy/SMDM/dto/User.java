package com.ssafy.SMDM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="user")
@Data
@Builder
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    private String userid;

    @Column(name="user_pw")
    private String userpw;

    @Column(name="user_birth")
    private String userbirth;

    @Column(name="gender")
    private Integer UserGender;

    @Column(name="memo")
    private String UserMemo;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();


//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    @Builder
    public User(String userid,String userpw,String userbirth,Integer userGender,String userMemo){
        this.userid = userid;
        this.userpw= userpw;
        this.userbirth = userbirth;
        this.UserGender = userGender;
        this.UserMemo = userMemo;
    }

}
