package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.User;
import com.ssafy.SMDM.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;


    private void checkPass(String plainPassword, String hasedPassword)
    { if (BCrypt.checkpw(plainPassword, hasedPassword)) {


        }
    }

        //id와 비밀번호를 통해 찾기
    public Boolean findByUserIdAndUserPw(String UserId,String UserPw){
        Optional<User> user = Optional.ofNullable(userRepository.findByUserid(UserId));
        if(user.isPresent()){
            if (BCrypt.checkpw(UserPw, user.get().getUserpw())) {
                return true;
            }
            else
                return false;
        }
        return false;
    }

    //id로 찾기
    public Optional<User> findByUserId(String UserId){
        Optional<User> user = Optional.ofNullable(userRepository.findByUserid(UserId));
        return user;
    }

    //delete
    @Transactional
    public void deleteByUserId(String UserId){
        userRepository.deleteByUserid(UserId);
    }

    //save
    public User save(User user){
        userRepository.save(user);
        return user;
    }

    //modified user for update
    public void updateByUserId(String UserId, String Updatepw){
        Optional<User> u = userRepository.findById(UserId);

        if(u.isPresent()){
            u.get().setUserpw(Updatepw);
            userRepository.save(u.get());
        }
    }

    //modified user for Memo
    public void updateByMemo(String UserId, String Memo){
        Optional<User> u = userRepository.findById(UserId);

        if(u.isPresent()){
            u.get().setUserMemo(Memo);
            userRepository.save(u.get());
        }

    }
}