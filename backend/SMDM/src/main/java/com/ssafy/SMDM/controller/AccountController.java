package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.User;
import com.ssafy.SMDM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    UserService userService;

    //로그인부분 추후에 JWT , Spring Sercurity 필요
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        Optional<User> u = userService.findByUserIdAndUserPw(user.getUserid(), user.getUserpw());
        if(u.isPresent()){
            //로그인 완료부분
            return ResponseEntity.ok();
        }else{
            //로그인 실패부분
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    //회원가입
    @PostMapping("/users")
    public Object SubmitUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
    }

    //id 중복체크
    @PostMapping("/users/id")
    public Object Idcheck(@RequestBody String id){
        Optional<User> u = userService.findByUserId(id);
        if(u.isPresent()) {
            //아이디가 존재
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK);
        }
    }

    //회원삭제
    @DeleteMapping("/users")
    public Object DeleteUser(@PathVariable String id){
        userService.deleteByUserId(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //회원정보 가져오기
    @GetMapping("/users")
    public Object GetUser(@RequestParam String id){
        Optional<User> u = userService.findByUserId(id);
        return new ResponseEntity<User>(u.get(),HttpStatus.OK);
    }
    
    //회원정보수정
    @PutMapping("/users")
    public Object UpdateUser(@PathVariable String id, User Muser){
        userService.updateByUserId(id,Muser);
        return new ResponseEntity<User>(Muser,HttpStatus.OK);
    }

    //메모작성
    @PutMapping("/users/memo")
    public Object UpdateMemo(@RequestParam("userid") String id,@RequestParam("memo") String memo){
        System.out.println(id + " " + memo);
        userService.updateByMemo(id,memo);
        return new ResponseEntity<String>(memo,HttpStatus.OK);

    }

    //메모확인
    @GetMapping("/users/memo")
    public Object GetMemo(@RequestParam("userid") String id){
        Optional<User> u = userService.findByUserId(id);
        return new ResponseEntity<String>(u.get().getUserMemo(),HttpStatus.OK);
    }
}
