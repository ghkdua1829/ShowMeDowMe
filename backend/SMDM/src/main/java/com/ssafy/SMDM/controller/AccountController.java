package com.ssafy.SMDM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.SMDM.repository.User;
import com.ssafy.SMDM.repository.UserRepo;
import com.ssafy.SMDM.service.JWTService;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private UserRepo userrepo;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public Object login(@RequestBody User user){

        ResponseEntity response = null;
        User user1 = this.userrepo.findUSER
                (user.getUser_id(),user.getUser_pw());
        if(user1 != null) {
            System.out.println("login");
            //jwt 키 생성
            String Jwttoken = jwtService.create("user_id",user.getUser_id(),user.getUser_birth());
            response = new ResponseEntity(Jwttoken, HttpStatus.OK);
            return ResponseEntity.ok(Jwttoken);
        } else{
            response = new ResponseEntity("not found", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not correct");
        }
    }
}
