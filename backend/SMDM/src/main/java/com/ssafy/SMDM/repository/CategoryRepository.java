package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.Category;
import com.ssafy.SMDM.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,String> {
    public Category findByCategoryid(String Categoryid);
}
