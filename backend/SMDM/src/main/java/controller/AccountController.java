package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.User;
import repository.UserRepo;
import service.JWTService;

import java.util.Optional;


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
            response = new ResponseEntity("success", HttpStatus.OK);
        } else{
            response = new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
