package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.DailyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyProductRepository extends JpaRepository<DailyProduct,Integer> {
    public DailyProduct findByUseridAndCategoryid(String UserId,String CategoryId);
    public void deleteByUseridAndCategoryid(String UserId,String CategoryId);

    @Query("SELECT p FROM daily_products as p where p.userid = :UserId")
    public List<DailyProduct> findByUserid(String UserId);
    @Query("SELECT p.categoryid FROM daily_products as p where p.userid = :UserId")
    public List<String> findByUser(String UserId);
}
