package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="daily_products")
@Data
@NoArgsConstructor
public class DailyProduct {

    @EmbeddedId
    private DailyProductPK dailyproductpk;

    @Column(name="date")
    private String date;

    @Builder
    public DailyProduct(String userid,String categoryid,String date){
        dailyproductpk.setUserid(userid);
        dailyproductpk.setCategoryid(categoryid);
        this.date = date;
    }
}
