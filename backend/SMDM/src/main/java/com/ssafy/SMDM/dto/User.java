package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="user")
@Data
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

    @Builder
    public User(String userId, String userPw, String userBirth, Integer userGender, String userMemo) {
        userid = userId;
        userpw = userPw;
        userbirth = userBirth;
        UserGender = userGender;
        UserMemo = userMemo;
    }

}
