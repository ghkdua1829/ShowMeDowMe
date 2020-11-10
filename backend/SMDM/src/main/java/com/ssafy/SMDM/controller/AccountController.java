package com.ssafy.SMDM.controller;

//import com.ssafy.SMDM.JWT.JwtTokenProvider;
import com.ssafy.SMDM.dto.User;
import com.ssafy.SMDM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountController {

//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;



    @Autowired
    UserService userService;


    // 나지금 mysql로 연동시켜놔서 applicaion.yml에 가서 mariadb나 컴터에있는 디비로 바꿔주면될거야 id 비번?
    // post 부분은 밑처럼 주면될듯! 이런식으로 json으로
    // {
    // "userid":"testid123",
    // "userpw":"testpw123"
    // }
    // 로그인부분 추후에 JWT , Spring Sercurity 필요
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> param) {
        boolean userfind = userService.findByUserIdAndUserPw(param.get("userid"), param.get("userpw"));
        if (userfind) {
            // 로그인 완료부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.OK);
        } else {
            // 로그인 실패부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.NOT_FOUND);
        }
    }

    // 회원가입 , 남과 여 는 0 1 로 설정하였음.
    @PostMapping("/users")
    public Object SubmitUser(@RequestBody Map<String, String> param) {
        User user = new User();
        user.setUserid(param.get("userid"));
        String CryptPw = BCrypt.hashpw(param.get("userpw"), BCrypt.gensalt());
        System.out.println(CryptPw);
        user.setUserpw(CryptPw);
        user.setUserbirth(param.get("userbirth"));
        user.setUserGender(Integer.parseInt(param.get("usergender")));
        userService.save(user);
//        userService.save(User.builder().
//                userid(param.get("userid")).
//                userpw(param.get("userpw")).
//                userbirth(param.get("userbirth")).
//                UserGender(Integer.parseInt(param.get("usergender"))).
//                roles(Collections.singletonList("ROLE_USER"))
//                .build()).getUserid();
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    // id 중복체크
    @PostMapping("/users/id")
    public Object Idcheck(@RequestBody Map<String, String> param) {
        Optional<User> u = userService.findByUserId(param.get("userid"));
        System.out.println(param.get("userid"));
        if (u.isPresent()) {
            // 아이디가 존재
            return new ResponseEntity<User>(u.get(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.OK);
        }
    }

    // 여기서부턴 RequestParam!
    // http://localhost:8080/api/users?userid=
    // 회원삭제
    @DeleteMapping("/users")
    public Object DeleteUser(@RequestParam("userid") String id) {
        Optional<User> u = userService.findByUserId(id);
        if(u.isPresent()) {
            userService.deleteByUserId(id);
            return new ResponseEntity<String>("Delete", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Id not Found", HttpStatus.NOT_FOUND);
        }
    }

    // 회원정보 가져오기
    @GetMapping("/users")
    public Object GetUser(@RequestParam("userid") String id) {
        Optional<User> u = userService.findByUserId(id);
        if (u.isPresent())
            return new ResponseEntity<User>(u.get(), HttpStatus.OK);
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    // 회원정보수정 -> 이거 user객체로 줄수있으면 이대로 가도 되는데 아니라면, 주석처리한부분 해야할듯?
    // 편한대로 말해주면 수정해줄께 어차피 얼마안걸림!
    // modified user = Muser
    //비밀번호 수정만!
    @PostMapping("/users/update")
    public Object UpdateUser(@RequestBody Map<String, String> param) {

        /*
         * Optional<User> Muser = userService.findByUserId(id);
         * Muser.get().setUserid(id); Muser.get().setUserpw(pw);
         * Muser.get().setUserbirth(birth);
         * Muser.get().setUserGender(Integer.parseInt(gender));
         *
         * userService.updateByUserId(id,Muser.get());
         */
        boolean userfind = userService.findByUserIdAndUserPw(param.get("userid"), param.get("userpw"));
        if (userfind) {
            // 로그인 완료부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.OK);
        } else {
            // 로그인 실패부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.NOT_FOUND);
        }
    }
    // 메모작성
    @PutMapping("/users/memo")
    public Object UpdateMemo(@RequestParam("userid") String id,@RequestParam("memo") String memo){
        userService.updateByMemo(id,memo);
        return new ResponseEntity<String>(memo,HttpStatus.OK);

    }

    // 메모확인
    @GetMapping("/users/memo")
    public Object GetMemo(@RequestParam("userid") String id) {
        Optional<User> u = userService.findByUserId(id);
        return new ResponseEntity<String>(u.get().getUserMemo(), HttpStatus.OK);
    }

    //비밀번호 확인
    @PostMapping("/users/confirm")
    public Object confirm(@RequestBody Map<String,String> param){
        boolean userfind = userService.findByUserIdAndUserPw(param.get("userid"), param.get("userpw"));
        if (userfind) {
            // 로그인 완료부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.OK);
        } else {
            // 로그인 실패부분
            return new ResponseEntity<String>(param.get("userid"), HttpStatus.NOT_FOUND);
        }
    }

}
