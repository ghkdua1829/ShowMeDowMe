package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.DailyProductPK;
import com.ssafy.SMDM.dto.DailyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyProductRepository extends JpaRepository<DailyProduct,String> {
    public DailyProduct findByDailProductPK(DailyProductPK pk);
    public void deleteByDailProductPK(DailyProductPK pk);
    public String findByDailProductPK_Userid(String UserId);
}
