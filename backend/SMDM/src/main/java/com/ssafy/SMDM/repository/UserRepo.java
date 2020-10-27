package com.ssafy.SMDM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

    @Query("SELECT u from User as u where u.user_id= :user_id and u.user_pw = :user_pw")
    User findUSER(String user_id, String user_pw);
}
