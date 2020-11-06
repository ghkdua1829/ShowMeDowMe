package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("User")
public interface UserRepository extends JpaRepository<User,String>{
    public User findByUserid(String UserId);
    public User findByUseridAndUserpw(String UserId,String UserPw);
    public void deleteByUserid(String UserId);
}