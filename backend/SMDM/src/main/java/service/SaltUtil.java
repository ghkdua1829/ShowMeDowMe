package service;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SaltUtil {
    public String encodePassword(String salt,String password){
        return BCrypt.hashpw(password,salt);
    }
    public String genSalt(){
        return BCrypt.gensalt();
    }
}
